package ru.nsu.fit.railway_station_backend.dto.service.event;

import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TopologyDto;

public abstract class Emergency extends Event {
    protected final TimetableDto timetable;
    protected final TopologyDto topology;

    public Emergency(TimetableDto timetable,
                     TopologyDto topology) {
        this.timetable = timetable;
        this.topology = topology;
    }
}
