package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dao.entity.Signal;
import ru.nsu.fit.railway_station_backend.dao.entity.Switch;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class NodeDto {

    private UUID id;

    private Set<TrackDto> inTracks;

    private Set<TrackDto> outTracks;

    private SignalDto signal;

    private SwitchDto aSwitch;

}
