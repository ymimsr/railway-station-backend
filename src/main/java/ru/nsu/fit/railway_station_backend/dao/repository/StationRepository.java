package ru.nsu.fit.railway_station_backend.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.railway_station_backend.dao.entity.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
}
