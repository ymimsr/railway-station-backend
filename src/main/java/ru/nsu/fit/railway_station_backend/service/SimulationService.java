package ru.nsu.fit.railway_station_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.railway_station_backend.dao.entity.Connection;
import ru.nsu.fit.railway_station_backend.dao.repository.ConnectionRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.StationRepository;

@Service
@RequiredArgsConstructor
public class SimulationService {

    private final StationRepository stationRepository;
    private final ConnectionRepository connectionRepository;



}
