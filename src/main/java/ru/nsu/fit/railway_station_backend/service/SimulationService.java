package ru.nsu.fit.railway_station_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.railway_station_backend.context.SimulationContext;
import ru.nsu.fit.railway_station_backend.dao.repository.ConnectionRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.StationRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.TimetableEntryRepository;
import ru.nsu.fit.railway_station_backend.dto.service.event.EventQueue;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableEntryDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.ConnectionDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.StationDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;
import ru.nsu.fit.railway_station_backend.mapping.ConnectionMapper;
import ru.nsu.fit.railway_station_backend.mapping.StationMapper;
import ru.nsu.fit.railway_station_backend.mapping.TimetableEntryMapper;
import ru.nsu.fit.railway_station_backend.util.Util;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final ConnectionRepository connectionRepository;
    private final ConnectionMapper connectionMapper;
    private final TimetableEntryRepository timetableEntryRepository;
    private final TimetableEntryMapper timetableEntryMapper;
    private final SimulationContext simulationContext;
    private final Planner planner;

    private EventQueue schedule;

    public void startSimulation() {
        prepareSimulationContext();
        buildSchedule();
    }

    public AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime> nextEvent() {
        return EventProcessor.processEvent(Objects.requireNonNull(schedule.poll()));
    }

    public void endSimulation() {
        simulationContext.clear();
        //buildSchedule();
    }

    private void prepareSimulationContext() {
        List<StationDto> stations = stationMapper
                .stationListToStationDtoList(stationRepository.findAll());
        List<ConnectionDto> connections = connectionMapper
                .connectionListToConnectionDtoList(connectionRepository.findAll());

        simulationContext.setStationDtoList(stations);

        Map<Long, StationDto> inputStations = new HashMap<>();
        Map<Long, StationDto> outputStations = new HashMap<>();
        for (StationDto station : stations) {
            inputStations.put(station.getId(), station);
            outputStations.put(station.getId(), station);

            simulationContext.getNodes().addAll(station.getNodes());
            simulationContext.getTracks().addAll(Util.getTracksFromNodes(station.getNodes()));
        }

        // adding connection tracks
        int idIndex = 0;
        for (ConnectionDto connection : connections) {
            for (NodeDto fromNode : connection.getFromStation().getInputNodes()) {
                for (NodeDto toNode : connection.getToStation().getOutputNodes()) {
                    TrackDto trackDto = new TrackDto();
                    trackDto.setId(1000L + idIndex++);
                    trackDto.setStartNode(fromNode);
                    trackDto.setEndNode(toNode);
                    trackDto.setLength(connection.getLength());
                    trackDto.setIsActive(true);
                    trackDto.setCanServe(Set.of(TrainType.PASS));

                    simulationContext.getTracks().add(trackDto);
                }
            }

            outputStations.remove(connection.getFromStation().getId());
            inputStations.remove(connection.getToStation().getId());
        }

        for (StationDto inputStation : inputStations.values()) {
            simulationContext.getInputNodes().addAll(inputStation.getInputNodes());
        }

        for (StationDto outputStation : outputStations.values()) {
            simulationContext.getOutputNodes().addAll(outputStation.getOutputNodes());
        }
    }

    private void buildSchedule() {
        List<TimetableEntryDto> timetable = timetableEntryMapper
                .timetableEntryDtoListFromTimetableEntryList(timetableEntryRepository.findAll());
        this.schedule = planner.createSchedule(timetable);
    }


}
