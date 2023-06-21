package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "node")
@Getter
@Setter
public class Node {

    @Column(name = "node_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "startNode", cascade = CascadeType.PERSIST)
    private Set<Track> inTracks;

    @OneToMany(mappedBy = "endNode", cascade = CascadeType.PERSIST)
    private Set<Track> outTracks;

    @OneToOne(mappedBy = "node", cascade = CascadeType.PERSIST)
    private Signal signal;

    @OneToOne(mappedBy = "node", cascade = CascadeType.PERSIST)
    private Switch aSwitch;

}
