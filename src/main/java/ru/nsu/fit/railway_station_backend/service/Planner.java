package ru.nsu.fit.railway_station_backend.service;

import lombok.RequiredArgsConstructor;
import org.projog.api.Projog;
import org.projog.api.QueryResult;
import org.projog.api.QueryStatement;
import org.projog.core.term.Term;
import org.springframework.stereotype.Service;
import ru.nsu.fit.railway_station_backend.context.SimulationContext;
import ru.nsu.fit.railway_station_backend.dao.repository.NodeRepository;
import ru.nsu.fit.railway_station_backend.dto.service.event.*;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.SignalState;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableEntryDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.*;

import java.time.LocalDateTime;
import java.util.*;

import static ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType.*;

@Service
@RequiredArgsConstructor
public class Planner {

    private final Projog projog;
    private final SimulationContext simulationContext;

    public EventQueue createSchedule(List<TimetableEntryDto> timetable){
        uploadTopology();

        return termToEventQueue(timetable, plan(timetable));
    }

    private EventQueue termToEventQueue(List<TimetableEntryDto> timetable, Term eventsTerm) {
        EventQueue eventQueue = new EventQueue();

        Term curTerm = eventsTerm;
        System.out.println(curTerm);
        while (curTerm.getNumberOfArguments() > 1) {
            System.out.println(curTerm.getArgs()[0].toString().replaceAll("[^a-zA-Z0-9 ]", ""));
            String[] stringEvent = curTerm.getArgs()[0].toString().replaceAll("[^a-zA-Z0-9 ]", "").split(" ");

            eventQueue.add(stringToEvent(timetable, stringEvent));

            curTerm = curTerm.getArgs()[1];
        }

        return eventQueue;
    }

    private AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime>, Event> stringToEvent(
            List<TimetableEntryDto> timetable, String[] stringEvent
    ) {
        int entryId = Integer.parseInt(stringEvent[1]);
        TimetableEntryDto timetableEntry = timetable
                .stream()
                .filter(entry -> entry.getTrain().getId() == entryId)
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalStateException("Couldn't find timetable entry from event in timetable");
                });

        int timeStart, timeEnd;
        Event event;
        switch (stringEvent[0]) {
            case "trainMoveEvent" -> {
                int fromTrackId = Integer.parseInt(stringEvent[2]);
                int toTrackId = Integer.parseInt(stringEvent[3]);
                timeStart = Integer.parseInt(stringEvent[4]);
                timeEnd = Integer.parseInt(stringEvent[5]);

                TrainDto train = timetableEntry.getTrain();

                TrackDto fromTrack = null;
                if (fromTrackId != 0)
                    fromTrack = simulationContext.getTracks()
                            .stream()
                            .filter(track -> track.getId() == fromTrackId)
                            .findFirst()
                            .orElseThrow(() -> {
                                throw new IllegalStateException("Couldn't find track from event in topology");
                            });

                TrackDto toTrack = simulationContext.getTracks()
                        .stream()
                        .filter(track -> track.getId() == toTrackId)
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new IllegalStateException("Couldn't find track from event in topology");
                        });

                event = new MoveTrainEvent(train, fromTrack, toTrack);
            }
            case "signalEvent" -> {
                int signalId = Integer.parseInt(stringEvent[2]);
                String signalValue = stringEvent[3];
                timeStart = Integer.parseInt(stringEvent[4]);
                timeEnd = Integer.parseInt(stringEvent[5]);

                SignalDto signal = simulationContext.getNodes()
                        .stream()
                        .map(NodeDto::getSignal)
                        .filter(signalElement -> signalElement.getId() == signalId)
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new IllegalStateException("Couldn't find signal from event in topology.");
                        });

                SignalState signalState = switch (signalValue) {
                    case "go" -> SignalState.GO;
                    case "stop" -> SignalState.STOP;
                    default -> throw new IllegalStateException("Invalid signal state");
                };

                event = new SignalEvent(signal, signalState);
            }
            case "switchEvent" -> {
                int switchId = Integer.parseInt(stringEvent[2]);
                int toTrackId = Integer.parseInt(stringEvent[3]);
                timeStart = Integer.parseInt(stringEvent[4]);
                timeEnd = Integer.parseInt(stringEvent[5]);

                SwitchDto aSwitch = simulationContext.getNodes()
                        .stream()
                        .map(NodeDto::getASwitch)
                        .filter(switchElement -> switchElement.getId() == switchId)
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new IllegalStateException("Couldn't find switch from event in topology.");
                        });

                TrackDto toTrack = simulationContext.getTracks()
                        .stream()
                        .filter(track -> track.getId() == toTrackId)
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new IllegalStateException("Couldn't find track from event in topology.");
                        });

                event = new SwitchEvent(aSwitch, toTrack);
            }
            default -> throw new IllegalStateException("Unknown event");
        }

        int eventTimeIntervalStartDiff = timetableEntry.getArrivalIntervalStart().getHour() * 60
                + timetableEntry.getArrivalIntervalStart().getMinute() - timeStart;

        int eventTimeIntervalEndDiff = timetableEntry.getArrivalIntervalFinish().getHour() * 60
                + timetableEntry.getArrivalIntervalFinish().getMinute() - timeEnd;

        LocalDateTime eventTimeIntervalStart =
                timetableEntry.getArrivalIntervalStart().minusMinutes(eventTimeIntervalStartDiff);

        LocalDateTime eventTimeIntervalEnd =
                timetableEntry.getArrivalIntervalFinish().minusMinutes(eventTimeIntervalEndDiff);

        return new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(eventTimeIntervalStart, eventTimeIntervalEnd), event);
    }

    private Term plan(List<TimetableEntryDto> timetable) {
        java.util.List<Long> longEntries = new ArrayList<>();
        java.util.List<String> typeEntries = new ArrayList<>();
        for (TimetableEntryDto entry : timetable) {
            longEntries.add(entry.getTrain().getId());
            longEntries.add(entry.getInputNode().getId());
            longEntries.add(entry.getEndNode().getId());
            longEntries.add(
                    (long) (entry.getArrivalIntervalStart().getHour() * 60 + entry.getArrivalIntervalStart().getMinute())
            );
            longEntries.add(
                    (long) (entry.getArrivalIntervalFinish().getHour() * 60 + entry.getArrivalIntervalFinish().getMinute())
            );
            longEntries.add((long) entry.getStationingTime());
            longEntries.add((long) entry.getTrain().getLength());

            typeEntries.add(switch (entry.getTrain().getTrainType()) {
                case PASSENGER -> "passenger";
                case PASS -> "pass";
                case MAINTENANCE -> "maintenance";
                case CARGO -> "cargo";
            });
        }

        QueryStatement planStatement = projog.createStatement(
                "planAdapter(LongEntries, TypeEntries, Events)."
        );
        planStatement.setListOfLongs("LongEntries", longEntries);
        planStatement.setListOfAtomNames("TypeEntries", typeEntries);

        System.out.println("planAdapter(LongEntries, TypeEntries, Events)."
                + " " + longEntries
                + " " + typeEntries);

        QueryResult result = planStatement.executeQuery();
        if (result.next()) {
            return result.getTerm("Events");
        }

        return null;
    }

    private void uploadTopology() {
        uploadNodes(simulationContext.getNodes());
        uploadTracks(simulationContext.getTracks());
    }

    private void uploadNodes(List<NodeDto> nodes) {
        for (NodeDto node : nodes) {
            Optional.ofNullable(node.getSignal())
                    .ifPresent(signalDto -> uploadSignal(node.getId(), signalDto));

            Optional.ofNullable(node.getASwitch())
                    .ifPresent(switchDto -> uploadSwitch(node.getId(), switchDto));

            java.util.List<Long> tracksIdIn = new ArrayList<>();
            for (TrackDto trackIn : node.getInTracks()) {
                tracksIdIn.add(trackIn.getId());
            }

            java.util.List<Long> tracksIdOut = new ArrayList<>();
            for (TrackDto trackOut : node.getOutTracks()) {
                tracksIdOut.add(trackOut.getId());
            }

            QueryStatement addNodeStatement = projog.createStatement("addNode(NodeId, TracksIdIn, TracksIdOut).");
            addNodeStatement.setLong("NodeId", node.getId());
            addNodeStatement.setListOfLongs("TracksIdIn", tracksIdIn);
            addNodeStatement.setListOfLongs("TracksIdOut", tracksIdOut);

//            System.out.println("addNode(NodeId, TracksIdIn, TracksIdOut)."
//                    + " " + node.getId()
//                    + " " + tracksIdIn
//                    + " " + tracksIdOut);

            System.out.println("addNode("
                    + node.getId()
                    + ", " + tracksIdIn
                    + ", " + tracksIdOut + ").");

            addNodeStatement.executeOnce();
        }
    }

    private void uploadTracks(List<TrackDto> tracks) {
        for (TrackDto track : tracks) {
            if (!track.getIsActive())
                continue;

            java.util.List<String> trackTypeAtoms = new ArrayList<>();
            for (TrainType type : track.getCanServe()) {
                trackTypeAtoms.add(switch (type) {
                    case PASS -> "pass";
                    case CARGO -> "cargo";
                    case PASSENGER -> "passenger";
                    case MAINTENANCE -> "maintenance";
                });
            }

            java.util.List<Long> tracksIdOut = new ArrayList<>();
            for (TrackDto trackOut: track.getEndNode().getOutTracks()) {
                tracksIdOut.add(trackOut.getId());
            }

            QueryStatement addTrackStatement = projog.createStatement(
                    "addTrack(TrackId, TrackLength, Type, TracksIdOut, NodeId)."
            );
            addTrackStatement.setLong("TrackId", track.getId());
            addTrackStatement.setLong("TrackLength", Long.valueOf(track.getLength()));
            addTrackStatement.setListOfAtomNames("Type", trackTypeAtoms);
            addTrackStatement.setListOfLongs("TracksIdOut", tracksIdOut);
            addTrackStatement.setLong("NodeId", track.getEndNode().getId());

//            System.out.println("addTrack(TrackId, TrackLength, Type, TracksIdOut, NodeId)."
//                    + " " + track.getId()
//                    + " " + Long.valueOf(track.getLength())
//                    + " " + trackTypeAtoms
//                    + " " + tracksIdOut
//                    + " " + track.getEndNode().getId());

            System.out.println("addTrack("
                    + track.getId()
                    + ", " + Long.valueOf(track.getLength())
                    + ", " + trackTypeAtoms
                    + ", " + tracksIdOut
                    + ", " + track.getEndNode().getId() + ").");

            addTrackStatement.executeOnce();
        }
    }

    private void uploadSwitch(Long nodeId, SwitchDto switchElement) {
        List<Long> toTracksId = new ArrayList<>();
        for (TrackDto toTrack : switchElement.getTracksTo()) {
            toTracksId.add(toTrack.getId());
        }

        QueryStatement addSwitchStatement = projog.createStatement(
                "addSwitch(SwitchId, NodeId, FromTrackId, ToTracksId)."
        );
        addSwitchStatement.setLong("SwitchId", switchElement.getId());
        addSwitchStatement.setLong("NodeId", nodeId);
        addSwitchStatement.setLong("FromTrackId",
                switchElement.getTrackFrom().getId()
        );
        addSwitchStatement.setListOfLongs("ToTracksId", toTracksId);

//        System.out.println("addSwitch(SwitchId, NodeId, FromTrackId, ToTracksId)."
//                + " " + switchElement.getId()
//                + " " + nodeId
//                + " " + switchElement.getTrackFrom().getId()
//                + " " + toTracksId);

        System.out.println("addSwitch("
                + switchElement.getId()
                + ", " + nodeId
                + ", " + switchElement.getTrackFrom().getId()
                + ", " + toTracksId + ").");

        addSwitchStatement.executeOnce();
    }

    private void uploadSignal(Long nodeId, SignalDto signal) {
        QueryStatement addSignalStatement = projog.createStatement(
                "addSignal(SignalId, NodeId)."
        );
        addSignalStatement.setLong("SignalId", signal.getId());
        addSignalStatement.setLong("NodeId", nodeId);

//        System.out.println("addSignal(SignalId, NodeId)."
//                + " " + signal.getId()
//                + " " + nodeId);

        System.out.println("addSignal("
                + signal.getId()
                + ", " + nodeId + ").");

        addSignalStatement.executeOnce();
    }

}
