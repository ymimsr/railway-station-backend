package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;

import java.util.List;

@Getter
@Setter
public class StationDto {

    private Long id;

    private List<Node> nodes;

    private List<Node> inputNodes;

    private List<Node> outputNodes;

}
