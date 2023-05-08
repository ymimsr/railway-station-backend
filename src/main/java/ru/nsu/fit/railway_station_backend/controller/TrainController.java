package ru.nsu.fit.railway_station_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.service.TrainService;

@RestController
@RequestMapping("/train")
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;




}
