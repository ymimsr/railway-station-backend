package ru.nsu.fit.railway_station_backend.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "node_id")
    private Node node;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_track")
    private Track fromTrack;

    @ManyToMany
    @JoinTable(
            name = "switch_to_tracks",
            joinColumns = { @JoinColumn(name = "switch_id") },
            inverseJoinColumns = { @JoinColumn(name = "track_id") }
    )
    private Set<Track> toTracks;

}
