package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.fit.railway_station_backend.dao.entity.Signal;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.SignalDto;

@Mapper
public interface SignalMapper {

    @Mapping(target = "node", ignore = true)
    SignalDto signalToSignalDtoWithoutRelations(Signal signal);

}
