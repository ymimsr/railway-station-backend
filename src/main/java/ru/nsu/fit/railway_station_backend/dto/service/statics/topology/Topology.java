package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Topology {

    private Set<Track> tracks;

    private Set<Node> nodes;

}
