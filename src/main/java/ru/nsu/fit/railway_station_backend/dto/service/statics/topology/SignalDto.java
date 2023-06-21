package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.SignalState;

import java.util.UUID;

@Getter
@Setter
public class SignalDto implements ControlElement {

    private UUID id;

    @JsonIgnore
    private NodeDto node;

    private SignalState currentState = SignalState.GO;

}
