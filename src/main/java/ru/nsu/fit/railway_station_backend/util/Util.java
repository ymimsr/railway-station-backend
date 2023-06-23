package ru.nsu.fit.railway_station_backend.util;

import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.NodeDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.StationDto;
import ru.nsu.fit.railway_station_backend.dto.service.statics.topology.TrackDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static List<TrackDto> getTracksFromNodes(List<NodeDto> nodeDtos) {
        List<TrackDto> result = new ArrayList<>();
        Map<Long, TrackDto> addedTracks = new HashMap<>();
        for (NodeDto node : nodeDtos) {
            for (TrackDto track : node.getInTracks()) {
                if (!addedTracks.containsKey(track.getId())) {
                    result.add(track);
                    addedTracks.put(track.getId(), track);
                }
            }

            for (TrackDto track : node.getOutTracks()) {
                if (!addedTracks.containsKey(track.getId())) {
                    result.add(track);
                    addedTracks.put(track.getId(), track);
                }
            }
        }

        return result;
    }

}
