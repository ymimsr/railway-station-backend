package ru.nsu.fit.railway_station_backend.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "track")
@Getter
@Setter
public class Track {

    @Column(name = "track_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "can_serve")
    @ElementCollection(targetClass = TrainType.class)
    @Enumerated
    private List<TrainType> canServe;

    @Column(name = "length")
    private Integer length;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "start_node_id")
    private Node startNode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "end_node_id")
    private Node endNode;

}
