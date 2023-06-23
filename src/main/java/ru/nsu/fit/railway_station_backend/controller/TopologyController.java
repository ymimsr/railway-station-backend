package ru.nsu.fit.railway_station_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.dao.entity.*;
import ru.nsu.fit.railway_station_backend.dao.repository.*;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.mapping.NodeMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/topology")
@RequiredArgsConstructor
public class TopologyController {

    private final NodeRepository nodeRepository;
    private final TrackRepository trackRepository;
    private final NodeMapper nodeMapper;
    private final TimetableEntryRepository entryRepository;
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final ConnectionRepository connectionRepository;

    @GetMapping()
    public void uploadTestData() {
        uploadTestStation("Station #1");
//        Station station2 = new Station();
//        station2.setName("Station #2");
//
//        Node node1 = new Node();
//        Node node2 = new Node();
//
//        Track track1 = new Track();
//
//        node1.setOutTracks(List.of(track1));
//        node2.setInTracks(List.of(track1));
//
//        track1.setCanServe(List.of(TrainType.PASS));
//        track1.setLength(1000);
//        track1.setStartNode(node1);
//        track1.setEndNode(node2);
//
//        station2.setNodes(List.of(node1, node2));
//        station2.setInputNodes(List.of(node1));
//        station2.setInputNodes(List.of(node2));
//
//        stationRepository.save(station2);
//
//        Connection connection = new Connection();
//        connection.setLength(1000);
//        connection.setFromStation(station1);
//        connection.setToStation(station2);
//
//        connectionRepository.save(connection);

        uploadTestTimetable();
    }

    @SuppressWarnings("all")
    private Station uploadTestStation(String stationName) {
        Station station = new Station();

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        Node node7 = new Node();
        Node node8 = new Node();

        Track track1 = new Track();
        Track track2 = new Track();
        Track track3 = new Track();
        Track track4 = new Track();
        Track track5 = new Track();
        Track track6 = new Track();
        Track track7 = new Track();
        Track track8 = new Track();

        Signal signal1 = new Signal();
        signal1.setNode(node2);
        //signal1.setCurrentState(SignalState.GO);

        Switch switch1 = new Switch();
        switch1.setNode(node6);
        switch1.setTrackFrom(track6);
        switch1.setTracksTo(Set.of(track7, track8));
        //switch1.setCurrentTrackTo(track8);

        node1.setInTracks(List.of());
        node1.setOutTracks(List.of(track1, track2));
        //node1.setControlElements(Set.of());

        node2.setInTracks(List.of(track1));
        node2.setOutTracks(List.of(track3));
        node2.setSignal(signal1);

        node3.setInTracks(List.of(track3));
        node3.setOutTracks(List.of(track5));
        //node3.setControlElements(Set.of());

        node4.setInTracks(List.of(track2));
        node4.setOutTracks(List.of(track4));
        //node4.setControlElements(Set.of());

        node5.setInTracks(List.of(track4));
        node5.setOutTracks(List.of(track6));
        //node5.setControlElements(Set.of());

        node6.setInTracks(List.of(track5, track6));
        node6.setOutTracks(List.of(track7, track8));
        node6.setASwitch(switch1);
        //node6.setControlElements(Set.of(switch1));

        node7.setInTracks(List.of(track7));
        node7.setOutTracks(List.of());
        //node7.setControlElements(Set.of());

        node8.setInTracks(List.of(track8));
        node8.setOutTracks(List.of());
        //node8.setControlElements(Set.of());

        track1.setStartNode(node1);
        track1.setEndNode(node2);
        track1.setLength(100);
        track1.setCanServe(List.of(TrainType.PASS));
        //track1.setCanServeInitial(Set.of(TrainType.PASS));
        //track1.setIsActive(true);

        track2.setStartNode(node1);
        track2.setEndNode(node4);
        track2.setLength(100);
        track2.setCanServe(List.of(TrainType.PASS));
        //track2.setCanServeInitial(Set.of(TrainType.PASS));
        //track2.setIsActive(true);

        track3.setStartNode(node2);
        track3.setEndNode(node3);
        track3.setLength(100);
        track3.setCanServe(List.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        //track3.setCanServeInitial(Set.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        //track3.setIsActive(true);

        track4.setStartNode(node4);
        track4.setEndNode(node5);
        track4.setLength(100);
        track4.setCanServe(List.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        //track4.setCanServeInitial(Set.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        //track4.setIsActive(true);

        track5.setStartNode(node3);
        track5.setEndNode(node6);
        track5.setLength(100);
        track5.setCanServe(List.of(TrainType.PASS));
        //track5.setCanServeInitial(Set.of(TrainType.PASS));
        //track5.setIsActive(true);

        track6.setStartNode(node5);
        track6.setEndNode(node6);
        track6.setLength(100);
        track6.setCanServe(List.of(TrainType.PASS));
        //track6.setCanServeInitial(Set.of(TrainType.PASS));
        //track6.setIsActive(true);

        track7.setStartNode(node6);
        track7.setEndNode(node7);
        track7.setLength(100);
        track7.setCanServe(List.of(TrainType.PASS));
        //track7.setCanServeInitial(Set.of(TrainType.PASS));
        //track7.setIsActive(true);

        track8.setStartNode(node6);
        track8.setEndNode(node8);
        track8.setLength(100);
        track8.setCanServe(List.of(TrainType.PASS));
        //track8.setCanServeInitial(Set.of(TrainType.PASS));
        //track8.setIsActive(true);

        station.setNodes(List.of(
                node1,
                node2,
                node3,
                node4,
                node5,
                node6,
                node7,
                node8
        ));

        station.setInputNodes(List.of(node1));
        station.setOutputNodes(List.of(node7, node8));
        station.setName(stationName);

        stationRepository.save(station);
        return station;
    }

    private void uploadTestTimetable() {
        LocalDate date = LocalDate.of(1970,1,1);

        List<Station> stations = stationRepository.findAll();

        Node startNode1 = stations.get(0).getInputNodes().get(0);

        Node finishNode1 = stations.get(1).getOutputNodes().get(0);
        //Node finishNode2 = stations.get(1).getOutputNodes().get(1);

        Train train1 = new Train();
        Train train2 = new Train();
        Train train3 = new Train();

        train1.setTrainType(TrainType.PASS);
        train1.setLength(1);
        train1.setSpeed(3);

        train2.setTrainType(TrainType.CARGO);
        train2.setLength(2);
        train1.setSpeed(2);

        train3.setTrainType(TrainType.PASSENGER);
        train3.setLength(2);
        train3.setSpeed(2);

        trainRepository.saveAll(List.of(train1,train2,train3));

        TimetableEntry entry1 = new TimetableEntry();
        TimetableEntry entry2 = new TimetableEntry();
        TimetableEntry entry3 = new TimetableEntry();

        entry1.setInputNode(finishNode1);
        entry1.setEndNode(startNode1);
        entry1.setArrivalIntervalStart(LocalDateTime.of(date, LocalTime.of(3,22)));
        entry1.setArrivalIntervalFinish(LocalDateTime.of(date, LocalTime.of(3,25)));
        entry1.setTrain(train1);
        entry1.setStationingTime(10);

        entry2.setInputNode(finishNode1);
        entry2.setEndNode(startNode1);
        entry2.setArrivalIntervalStart(LocalDateTime.of(date, LocalTime.of(10,50)));
        entry2.setArrivalIntervalFinish(LocalDateTime.of(date, LocalTime.of(10,57)));
        entry2.setTrain(train2);
        entry2.setStationingTime(40);

        entry3.setInputNode(finishNode1);
        entry3.setEndNode(startNode1);
        entry3.setArrivalIntervalStart(LocalDateTime.of(date, LocalTime.of(22,31)));
        entry3.setArrivalIntervalFinish(LocalDateTime.of(date, LocalTime.of(22,37)));
        entry3.setTrain(train3);
        entry3.setStationingTime(20);

        entryRepository.saveAll(List.of(entry1, entry2, entry3));
    }

    @GetMapping("/test-mapping")
    public List<NodeDto> nodeDtoList() {
        List<Node> nodes = nodeRepository.findAll();
        List<NodeDto> result = new ArrayList<>();
        for (Node x : nodes) {
            result.add(nodeMapper.nodeToNodeDto(x));
        }

        return result;
    }

}
