package ru.nsu.fit.railway_station_backend.service;

import lombok.RequiredArgsConstructor;
import org.projog.api.Projog;
import org.springframework.stereotype.Service;
import ru.nsu.fit.railway_station_backend.dao.repository.NodeRepository;

import java.awt.*;

@Service
@RequiredArgsConstructor
public class Planner {

    private final Projog projog;
    private final NodeRepository nodeRepository;



}
