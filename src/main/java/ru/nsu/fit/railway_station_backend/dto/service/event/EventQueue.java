package ru.nsu.fit.railway_station_backend.dto.service.event;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.PriorityQueue;

public class EventQueue extends PriorityQueue<AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime>, Event>> {

    public EventQueue() {
        super(java.util.Map.Entry.comparingByKey(java.util.Map.Entry.comparingByKey()));
    }
}
