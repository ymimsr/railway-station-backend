package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class SwitchDto implements ControlElement {

    private Long id;

    @JsonIgnore
    private NodeDto node;

    private TrackDto trackFrom;

    private Set<TrackDto> tracksTo;

    private TrackDto currentTrackTo;

}
