package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class TrackDto {

    private Long id;

    private Integer length;

    private Set<TrainType> canServe;

    private Boolean isActive = Boolean.TRUE;

    @JsonIgnore
    private NodeDto startNode;

    @JsonIgnore
    private NodeDto endNode;

    private TrainDto currentTrain;

}
