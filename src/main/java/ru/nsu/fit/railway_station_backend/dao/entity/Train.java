package ru.nsu.fit.railway_station_backend.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;

import java.util.UUID;

@Entity
@Table(name = "train")
@Getter
@Setter
public class Train {

    @Column(name = "train_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "length")
    private Integer length;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "train_type")
    @Enumerated(EnumType.STRING)
    private TrainType trainType;

}
