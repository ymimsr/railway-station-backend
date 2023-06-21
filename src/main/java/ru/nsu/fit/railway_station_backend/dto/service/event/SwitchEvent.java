package ru.nsu.fit.railway_station_backend.dto.service.event;

import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.SwitchDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

public class SwitchEvent extends Event {

    private final SwitchDto aSwitch;
    private final TrackDto toTrack;

    public SwitchEvent(SwitchDto aSwitch, TrackDto toTrack) {
        this.aSwitch = aSwitch;
        this.toTrack = toTrack;
    }

    @Override
    public void apply() {
        aSwitch.setCurrentTrackTo(toTrack);
    }
}
