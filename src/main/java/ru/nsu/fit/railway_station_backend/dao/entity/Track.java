package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "track")
@Getter
@Setter
public class Track {

    @Column(name = "track_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "can_serve")
    @ElementCollection(targetClass = TrainType.class)
    @Enumerated
    private Set<TrainType> canServe;

    @Column(name = "length")
    private Integer length;

    @ManyToOne
    @JoinColumn(name = "start_node_id")
    private Node startNode;

    @ManyToOne
    @JoinColumn(name = "end_node_id")
    private Node endNode;

}
