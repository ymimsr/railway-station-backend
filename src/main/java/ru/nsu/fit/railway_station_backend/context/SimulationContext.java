package ru.nsu.fit.railway_station_backend.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;
import ru.nsu.fit.railway_station_backend.dao.entity.Track;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.StationDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

import java.util.ArrayList;
import java.util.List;

// building node graph
@Component
@Getter
@Setter
public class SimulationContext {

    private List<StationDto> stationDtoList = new ArrayList<>();

    private List<NodeDto> nodes = new ArrayList<>();

    private List<NodeDto> inputNodes = new ArrayList<>();

    private List<NodeDto> outputNodes = new ArrayList<>();

    // including connection tracks
    private List<TrackDto> tracks = new ArrayList<>();

    public void clear() {
        nodes.clear();
        inputNodes.clear();
        outputNodes.clear();
        tracks.clear();
    }

}
