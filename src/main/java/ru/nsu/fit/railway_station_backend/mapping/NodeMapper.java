package ru.nsu.fit.railway_station_backend.mapping;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.Mapping;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.SwitchDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        TrackMapper.class,
        SwitchMapper.class,
        SignalMapper.class
})
public interface NodeMapper {

    NodeDto nodeToNodeDto(Node node);

    List<NodeDto> nodesToNodeDtos(List<Node> nodeDtos);

    @AfterMapping
    default void addRelations(@MappingTarget NodeDto nodeDto) {
         if (nodeDto.getASwitch() != null) {
            SwitchDto switchDto = nodeDto.getASwitch();

            switchDto.setNode(nodeDto);
            switchDto.setCurrentTrackTo(switchDto.getTracksTo()
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Mapping exception. Can't find switch's tracksTo"))
            );
        }

        if (nodeDto.getSignal() != null) {
            nodeDto.getSignal().setNode(nodeDto);
        }

        for (TrackDto inTrack : nodeDto.getInTracks()) {
            inTrack.setEndNode(nodeDto);
        }

        for (TrackDto outTrack : nodeDto.getOutTracks()) {
            outTrack.setStartNode(nodeDto);
        }
    }

}
