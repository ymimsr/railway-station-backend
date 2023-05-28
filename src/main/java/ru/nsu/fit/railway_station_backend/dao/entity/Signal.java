package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "signal")
@Getter
@Setter
public class Signal {

    @Column(name = "signal_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "nodeSignal")
    private Node node;

}
