package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "switch")
@Getter
@Setter
public class Switch {

    @Column(name = "switch_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "nodeSwitch")
    private Node node;

}
