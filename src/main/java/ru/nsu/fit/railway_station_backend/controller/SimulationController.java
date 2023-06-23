package ru.nsu.fit.railway_station_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.dao.entity.Track;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;
import ru.nsu.fit.railway_station_backend.dao.repository.NodeRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.StationRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.TrackRepository;
import ru.nsu.fit.railway_station_backend.dto.api.SimulationResponse;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.StationDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;
import ru.nsu.fit.railway_station_backend.mapping.NodeMapper;
import ru.nsu.fit.railway_station_backend.mapping.StationMapper;
import ru.nsu.fit.railway_station_backend.service.SimulationService;
import ru.nsu.fit.railway_station_backend.util.Util;

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

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final TrackRepository trackRepository;
    private final SimulationService service;

    private TrainDto trainDto;
    private LocalDateTime timestamp;


    @GetMapping
    public void startSimulation() {
//        trainDto = new TrainDto();
//        trainDto.setId(1L);
//        trainDto.setTrainType(TrainType.PASS);
//        trainDto.setLength(1);
//        trainDto.setSpeed(10);
//
//        timestamp = LocalDateTime.of(1970, 1, 1, 0, 0);
        service.startSimulation();
    }

    @GetMapping("/{stationId}/next")
    public SimulationResponse nextStep(@PathVariable Long stationId) {
        SimulationResponse simulationResponse = new SimulationResponse();
        StationDto station = stationMapper.stationToStationDto(stationRepository
                .findById(stationId)
                .orElseThrow(() -> new IllegalStateException("Can't find station with given id")));
        List<NodeDto> nodeDtos = station.getNodes();
        List<TrackDto> trackDtos = Util.getTracksFromNodes(station.getNodes());
        simulationResponse.setNodes(nodeDtos);
        simulationResponse.setTracks(trackDtos);
        simulationResponse.setTimestamp(service.nextEvent().getKey());

        return simulationResponse;
    }

}
