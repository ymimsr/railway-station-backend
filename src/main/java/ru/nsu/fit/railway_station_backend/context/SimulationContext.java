package ru.nsu.fit.railway_station_backend.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;

import java.util.ArrayList;
import java.util.List;

// building node graph
@Component
@Getter
@Setter
public class SimulationContext {

    private List<Node> nodes = new ArrayList<>();

    private List<Node> inputNodes = new ArrayList<>();

    private List<Node> outputNodes = new ArrayList<>();



}
