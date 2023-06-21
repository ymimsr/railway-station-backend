package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.fit.railway_station_backend.dao.entity.Switch;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.SwitchDto;

@Mapper(componentModel = "spring", uses = TrackMapper.class)
public interface SwitchMapper {

    @Mapping(target = "node", ignore = true)
    SwitchDto switchToSwitchDtoWithoutRelations(Switch aSwitch);

}
