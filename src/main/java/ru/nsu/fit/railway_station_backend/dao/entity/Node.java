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

    @OneToMany(mappedBy = "startNode")
    private Set<Track> inTracks;

    @OneToMany(mappedBy = "endNode")
    private Set<Track> outTracks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "signal_id", referencedColumnName = "signal_id")
    private Signal nodeSignal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "switch_id", referencedColumnName = "switch_id")
    private Switch nodeSwitch;

}
