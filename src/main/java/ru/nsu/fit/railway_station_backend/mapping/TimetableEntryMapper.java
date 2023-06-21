package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.fit.railway_station_backend.dao.entity.TimetableEntry;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableEntryDto;

@Mapper(componentModel = "spring", uses = TrainMapper.class)
public interface TimetableEntryMapper {
    @Mapping(target = "inputNode", ignore = true)
    @Mapping(target = "endNode", ignore = true)
    TimetableEntryDto timetableEntryDtoFromTimetableEntry(TimetableEntry entry);
}
