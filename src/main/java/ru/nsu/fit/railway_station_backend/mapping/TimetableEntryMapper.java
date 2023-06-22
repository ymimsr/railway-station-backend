package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import ru.nsu.fit.railway_station_backend.dao.entity.TimetableEntry;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableEntryDto;

@Mapper(componentModel = "spring", uses = {NodeMapper.class, TrainMapper.class})
public interface TimetableEntryMapper {

    TimetableEntryDto timetableEntryDtoFromTimetableEntry(TimetableEntry entry);

}
