package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;
import ru.nsu.fit.railway_station_backend.dao.entity.Track;

import java.util.Set;

@Getter
@Setter
public class Topology {

    private Set<Track> tracks;

    private Set<Node> nodes;

}
