package ru.nsu.fit.railway_station_backend.dto.service.statics.timetable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TimetableEntryDto {

    private UUID id;

    private LocalDateTime arrivalTime;

    private Integer stationingTime;

    private TrainDto train;

    @JsonIgnore
    private NodeDto inputNode;

    @JsonIgnore
    private NodeDto endNode;
}
