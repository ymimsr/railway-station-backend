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

    @Column(name = "station_name")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "station_nodes")
    private List<Node> nodes;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "station_input_nodes")
    private List<Node> inputNodes;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "station_output_nodes")
    private List<Node> outputNodes;

}
