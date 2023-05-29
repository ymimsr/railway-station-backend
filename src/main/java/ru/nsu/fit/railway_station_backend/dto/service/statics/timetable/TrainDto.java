package ru.nsu.fit.railway_station_backend.dto.service.statics.timetable;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

import java.util.UUID;

@Getter
@Setter
public class TrainDto {

    private UUID id;

    private Integer length;

    private Integer speed;

    private TrainType trainType;

    private TrackDto occupiedTrack;

}
