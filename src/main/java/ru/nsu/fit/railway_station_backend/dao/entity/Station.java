package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "station")
@Getter
@Setter
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Node> nodes;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Node> inputNodes;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Node> outputNode;

}
