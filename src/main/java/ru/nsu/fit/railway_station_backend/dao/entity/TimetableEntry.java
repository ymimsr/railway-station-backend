package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "timetable")
@Getter
@Setter
public class TimetableEntry {

    @Column(name = "timetable_entry_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "stationing_time")
    private Integer stationingTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "input_node", nullable = false)
    private Node inputNode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "output_node", nullable = false)
    private Node endNode;

}
