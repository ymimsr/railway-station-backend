package ru.nsu.fit.railway_station_backend.dto.service.topology;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Node {

    private UUID id;

    private Set<Track> inTracks;

    private Set<Track> outTracks;

    private Set<ControlElement> controlElements;

}
