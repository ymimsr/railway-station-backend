package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TrainDto;

@Mapper
public interface TrainMapper {

    TrainDto trainToTrainDto(Train train);

}
