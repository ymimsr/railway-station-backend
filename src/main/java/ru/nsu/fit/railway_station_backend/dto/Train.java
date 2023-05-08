package ru.nsu.fit.railway_station_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Train {

    private UUID uuid;

    private Integer length;

    private Integer speed;

    private TrainType trainType;
}
