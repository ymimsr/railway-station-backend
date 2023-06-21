package ru.nsu.fit.railway_station_backend.dto.service.event;

import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.SignalState;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.SignalDto;

public class SignalEvent extends Event {
    private final SignalDto signal;
    private final SignalState newSignalState;

    public SignalEvent(SignalDto signal, SignalState newSignalState) {
        this.signal = signal;
        this.newSignalState = newSignalState;
    }

    @Override
    public void apply() {
        signal.setCurrentState(newSignalState);
    }
}
