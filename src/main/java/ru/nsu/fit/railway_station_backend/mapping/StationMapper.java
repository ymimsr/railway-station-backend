package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import ru.nsu.fit.railway_station_backend.dao.entity.Station;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.StationDto;

@Mapper(componentModel = "spring", uses = NodeMapper.class)
public interface StationMapper {

    StationDto stationToStationDto(Station station);

}
