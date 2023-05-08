package ru.nsu.fit.railway_station_backend.dto.service.topology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Switch implements ControlElement {

    private UUID id;

    @JsonIgnore
    private Node node;

    private Track trackFrom;

    private Set<Track> tracksTo;

    private Track currentTrackTo;

}
