package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import ru.nsu.fit.railway_station_backend.dao.entity.Connection;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.ConnectionDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = StationMapper.class)
public interface ConnectionMapper {

    ConnectionDto connectionToConnectionDto(Connection connection);

    List<ConnectionDto> connectionListToConnectionDtoList(List<Connection> connectionList);

}
