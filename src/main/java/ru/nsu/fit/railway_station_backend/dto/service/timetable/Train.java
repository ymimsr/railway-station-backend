package ru.nsu.fit.railway_station_backend.dto.service.timetable;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.topology.Track;

import java.util.UUID;

@Getter
@Setter
public class Train {

    private UUID uuid;

    private Integer length;

    private Integer speed;

    private TrainType trainType;

    private Track occupiedTrack;

}
