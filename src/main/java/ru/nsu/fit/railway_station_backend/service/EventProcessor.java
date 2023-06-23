package ru.nsu.fit.railway_station_backend.service;

import org.springframework.stereotype.Service;
import ru.nsu.fit.railway_station_backend.dto.service.event.Event;
import ru.nsu.fit.railway_station_backend.dto.service.event.EventQueue;

import java.time.LocalDateTime;
import java.util.AbstractMap;

public class EventProcessor {

    //private final EmergencyGenerator emergencyGenerator;

    public static AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime> processEvent(
            AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime>, Event> eventPair
    ) {
        AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime> interval = eventPair.getKey();
        //LocalDateTime timestamp = eventPair.getKey().getValue();
        Event event = eventPair.getValue();

        //TODO normal implementation for emergency generation
        /*Emergency generated = emergencyGenerator.roll();
        if (generated != null) {
            LocalDateTime randomDateTime = RandomDateTimeGenerator.after(timestamp);
            eventQueue.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(randomDateTime, randomDateTime), generated));
        }*/
        event.apply();
        return interval;
    }

}
