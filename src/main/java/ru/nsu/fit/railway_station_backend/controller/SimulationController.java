package ru.nsu.fit.railway_station_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.dao.entity.Track;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;
import ru.nsu.fit.railway_station_backend.dao.repository.NodeRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.TrackRepository;
import ru.nsu.fit.railway_station_backend.dto.api.SimulationResponse;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;
import ru.nsu.fit.railway_station_backend.mapping.NodeMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {

    private final NodeMapper nodeMapper;
    private final NodeRepository nodeRepository;
    private final TrackRepository trackRepository;

    private TrainDto trainDto;
    private LocalDateTime timestamp;


    @GetMapping
    public void startSimulation() {
        trainDto = new TrainDto();
        trainDto.setId(UUID.randomUUID());
        trainDto.setTrainType(TrainType.PASS);
        trainDto.setLength(1);
        trainDto.setSpeed(10);

        timestamp = LocalDateTime.of(1970, 1, 1, 0, 0);
    }

    @GetMapping("/next")
    public SimulationResponse nextStep() {
        SimulationResponse simulationResponse = new SimulationResponse();
        List<NodeDto> nodeDtos = nodeMapper.nodesToNodeDtos(nodeRepository.findAll());
        List<Track> trackDtos = trackRepository.findAll();
        simulationResponse.setNodes(nodeDtos);
        simulationResponse.setTracks(trackDtos);
        simulationResponse.setTimestamp(timestamp);
        timestamp = timestamp.plus(10, ChronoUnit.MINUTES);

        TrackDto randomTrack = null;
        while (randomTrack == null) {
            int randomNodeIndex = ThreadLocalRandom.current().nextInt(0, nodeDtos.size());
            randomTrack = nodeDtos.get(randomNodeIndex)
                    .getInTracks()
                    .stream()
                    .findAny()
                    .orElse(null);

            if (randomTrack != null)
                break;

            randomTrack = nodeDtos.get(randomNodeIndex)
                    .getOutTracks()
                    .stream()
                    .findAny()
                    .orElse(null);
        }

        trainDto.setOccupiedTrack(randomTrack);

        simulationResponse.setTrains(List.of(trainDto));

        return simulationResponse;
    }

}
