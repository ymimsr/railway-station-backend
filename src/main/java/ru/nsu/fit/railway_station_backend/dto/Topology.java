package ru.nsu.fit.railway_station_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Topology {

    private Set<Track> tracks;

    private Set<Node> nodes;

}
