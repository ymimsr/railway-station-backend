package ru.nsu.fit.railway_station_backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.railway_station_backend.dao.entity.Switch;

import java.util.UUID;

public interface SwitchRepository extends JpaRepository<Switch, Long> {
}
