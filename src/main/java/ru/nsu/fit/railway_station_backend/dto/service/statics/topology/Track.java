package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Track {

    private UUID uuid;

    private Integer length;

    private Set<TrainType> canServe;

    private Set<TrainType> canServeInitial;

    private Boolean isActive;

    @JsonIgnore
    private Node startNode;

    @JsonIgnore
    private Node endNode;

}
