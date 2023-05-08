package ru.nsu.fit.railway_station_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Signal implements ControlElement {

    private UUID id;

    @JsonIgnore
    private Node node;

    private SignalState currentState;

}
