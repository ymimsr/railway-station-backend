package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.fit.railway_station_backend.dao.entity.Track;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

@Mapper
public interface TrackMapper {

    @Mapping(target = "startNode", ignore = true)
    @Mapping(target = "endNode", ignore = true)
    TrackDto trackDtoFromTrackWithoutRelations(Track track);

}
