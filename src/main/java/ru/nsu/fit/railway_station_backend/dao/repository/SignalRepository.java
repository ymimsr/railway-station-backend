package ru.nsu.fit.railway_station_backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.railway_station_backend.dao.entity.Signal;

import java.util.UUID;

public interface SignalRepository extends JpaRepository<Signal, UUID> {
}
