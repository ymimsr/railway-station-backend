package ru.nsu.fit.railway_station_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.dto.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/topology")
public class TopologyController {

    @GetMapping("/topology")
    @SuppressWarnings("all")
    public Topology getTopology() {
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
        signal1.setId(UUID.randomUUID());
        signal1.setNode(node2);
        signal1.setCurrentState(SignalState.GO);

        Switch switch1 = new Switch();
        switch1.setId(UUID.randomUUID());
        switch1.setNode(node6);
        switch1.setTrackFrom(track6);
        switch1.setTracksTo(Set.of(track7, track8));
        switch1.setCurrentTrackTo(track8);

        node1.setId(UUID.randomUUID());
        node1.setInTracks(Set.of());
        node1.setOutTracks(Set.of(track1, track2));
        node1.setControlElements(Set.of());

        node2.setId(UUID.randomUUID());
        node2.setInTracks(Set.of(track1));
        node2.setOutTracks(Set.of(track3));
        node2.setControlElements(Set.of(signal1));

        node3.setId(UUID.randomUUID());
        node3.setInTracks(Set.of(track3));
        node3.setOutTracks(Set.of(track5));
        node3.setControlElements(Set.of());

        node4.setId(UUID.randomUUID());
        node4.setInTracks(Set.of(track2));
        node4.setOutTracks(Set.of(track4));
        node4.setControlElements(Set.of());

        node5.setId(UUID.randomUUID());
        node5.setInTracks(Set.of(track4));
        node5.setOutTracks(Set.of(track6));
        node5.setControlElements(Set.of());

        node6.setId(UUID.randomUUID());
        node6.setInTracks(Set.of(track5, track6));
        node6.setOutTracks(Set.of(track7, track8));
        node6.setControlElements(Set.of(switch1));

        node7.setId(UUID.randomUUID());
        node7.setInTracks(Set.of(track7));
        node7.setOutTracks(Set.of());
        node7.setControlElements(Set.of());

        node8.setId(UUID.randomUUID());
        node8.setInTracks(Set.of(track8));
        node8.setOutTracks(Set.of());
        node8.setControlElements(Set.of());

        track1.setUuid(UUID.randomUUID());
        track1.setStartNode(node1);
        track1.setEndNode(node2);
        track1.setLength(100);
        track1.setCanServe(Set.of(TrainType.PASS));
        track1.setCanServeInitial(Set.of(TrainType.PASS));
        track1.setIsActive(true);

        track2.setUuid(UUID.randomUUID());
        track2.setStartNode(node1);
        track2.setEndNode(node4);
        track2.setLength(100);
        track2.setCanServe(Set.of(TrainType.PASS));
        track2.setCanServeInitial(Set.of(TrainType.PASS));
        track2.setIsActive(true);

        track3.setUuid(UUID.randomUUID());
        track3.setStartNode(node2);
        track3.setEndNode(node3);
        track3.setLength(100);
        track3.setCanServe(Set.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        track3.setCanServeInitial(Set.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        track3.setIsActive(true);

        track4.setUuid(UUID.randomUUID());
        track4.setStartNode(node4);
        track4.setEndNode(node5);
        track4.setLength(100);
        track4.setCanServe(Set.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        track4.setCanServeInitial(Set.of(TrainType.PASS, TrainType.CARGO, TrainType.PASSENGER));
        track4.setIsActive(true);

        track5.setUuid(UUID.randomUUID());
        track5.setStartNode(node3);
        track5.setEndNode(node6);
        track5.setLength(100);
        track5.setCanServe(Set.of(TrainType.PASS));
        track5.setCanServeInitial(Set.of(TrainType.PASS));
        track5.setIsActive(true);

        track6.setUuid(UUID.randomUUID());
        track6.setStartNode(node5);
        track6.setEndNode(node6);
        track6.setLength(100);
        track6.setCanServe(Set.of(TrainType.PASS));
        track6.setCanServeInitial(Set.of(TrainType.PASS));
        track6.setIsActive(true);

        track7.setUuid(UUID.randomUUID());
        track7.setStartNode(node6);
        track7.setEndNode(node7);
        track7.setLength(100);
        track7.setCanServe(Set.of(TrainType.PASS));
        track7.setCanServeInitial(Set.of(TrainType.PASS));
        track7.setIsActive(true);

        track8.setUuid(UUID.randomUUID());
        track8.setStartNode(node6);
        track8.setEndNode(node8);
        track8.setLength(100);
        track8.setCanServe(Set.of(TrainType.PASS));
        track8.setCanServeInitial(Set.of(TrainType.PASS));
        track8.setIsActive(true);

        Topology topology = new Topology();
        topology.setNodes(Set.of(
                node1,
                node2,
                node3,
                node4,
                node5,
                node6,
                node7,
                node8
        ));
        topology.setTracks(Set.of(
                track1,
                track2,
                track3,
                track4,
                track5,
                track6,
                track7,
                track8
        ));

        return topology;
    }

}
