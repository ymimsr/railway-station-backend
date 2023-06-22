package ru.nsu.fit.railway_station_backend.dto.service.statics.topology;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.railway_station_backend.dao.entity.Station;

@Getter
@Setter
public class ConnectionDto {

    private Long id;

    private Station inStation;

    private Station outStation;

}
