package ru.nsu.fit.railway_station_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;
import ru.nsu.fit.railway_station_backend.dao.repository.NodeRepository;
import ru.nsu.fit.railway_station_backend.dto.api.SimulationResponse;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;
import ru.nsu.fit.railway_station_backend.mapping.NodeMapper;

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
    private TrainDto trainDto;
    private List<NodeDto> nodeDtos;


    @GetMapping
    public void startSimulation() {
        trainDto = new TrainDto();
        trainDto.setId(UUID.randomUUID());
        trainDto.setTrainType(TrainType.PASS);
        trainDto.setLength(1);
        trainDto.setSpeed(10);
    }

    @GetMapping("/next")
    public SimulationResponse nextStep() {
        SimulationResponse simulationResponse = new SimulationResponse();
        this.nodeDtos = nodeMapper.nodesToNodeDtos(nodeRepository.findAll());
        simulationResponse.setNodes(nodeDtos);

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
