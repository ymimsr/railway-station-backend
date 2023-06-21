package ru.nsu.fit.railway_station_backend.dto.service.event;

import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

public class MoveTrainEvent extends Event {
    private final TrainDto train;
    private final TrackDto toTrack;

    public MoveTrainEvent(TrainDto train, TrackDto toTrack) {
        this.train = train;
        this.toTrack = toTrack;
    }

    @Override
    public void apply() {
        train.setOccupiedTrack(toTrack);
    }

    @Override
    public String toString() {
        return "MoveTrainEvent{" +
                "train=" + train.getId() +
                ", toTrack=" + (toTrack != null ? toTrack.getId() : -1) +
                '}';
    }
}
