package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;

import java.util.List;

@Getter
@Setter
public class StationDto {

    private Long id;

    private String name;

    private List<NodeDto> nodes;

    private List<NodeDto> inputNodes;

    private List<NodeDto> outputNodes;

}
