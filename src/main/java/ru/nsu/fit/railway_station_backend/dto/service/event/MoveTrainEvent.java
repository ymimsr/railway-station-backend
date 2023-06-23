package ru.nsu.fit.railway_station_backend.dto.service.event;

import lombok.RequiredArgsConstructor;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

@RequiredArgsConstructor
public class MoveTrainEvent extends Event {

    private final TrainDto train;
    private final TrackDto fromTrack;
    private final TrackDto toTrack;

    @Override
    public void apply() {
        if (fromTrack != null)
            fromTrack.setCurrentTrain(null);
        toTrack.setCurrentTrain(train);
        train.setOccupiedTrack(toTrack);
    }

    @Override
    public String toString() {
        return "MoveTrainEvent{" +
                "train=" + train +
                ", fromTrack=" + fromTrack +
                ", toTrack=" + toTrack +
                '}';
    }
}
