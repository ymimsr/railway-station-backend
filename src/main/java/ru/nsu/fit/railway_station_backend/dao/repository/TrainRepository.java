package ru.nsu.fit.railway_station_backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;

import java.util.UUID;

public interface TrainRepository extends JpaRepository<Train, UUID> {
}
