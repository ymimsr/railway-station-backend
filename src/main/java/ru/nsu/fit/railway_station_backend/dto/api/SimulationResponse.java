package ru.nsu.fit.railway_station_backend.dto.api;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TopologyDto;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SimulationResponse {

    private List<NodeDto> nodes;
    private List<TrainDto> trains;

}
