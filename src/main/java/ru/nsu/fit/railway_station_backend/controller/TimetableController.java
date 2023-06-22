package ru.nsu.fit.railway_station_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.railway_station_backend.dao.entity.Node;
import ru.nsu.fit.railway_station_backend.dao.entity.TimetableEntry;
import ru.nsu.fit.railway_station_backend.dao.entity.Train;
import ru.nsu.fit.railway_station_backend.dao.repository.NodeRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.TimetableEntryRepository;
import ru.nsu.fit.railway_station_backend.dao.repository.TrainRepository;
import ru.nsu.fit.railway_station_backend.dto.service.statics.enums.TrainType;
import ru.nsu.fit.railway_station_backend.dto.service.statics.timetable.TimetableEntryDto;
import ru.nsu.fit.railway_station_backend.mapping.TimetableEntryMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableEntryMapper entryMapper;
    private final TimetableEntryRepository entryRepository;
    private final NodeRepository nodeRepository;
    private final TrainRepository trainRepository;

    @GetMapping()
    @SuppressWarnings("all")
    public void uploadTestTimetable() {
        LocalDate date = LocalDate.of(1970,1,1);

        List<Node> nodes = nodeRepository.findAll();
        Node startNode = nodes.get(0);
        Node finishNode = nodes.get(nodes.size()-1);

        Train train1 = new Train();
        Train train2 = new Train();
        Train train3 = new Train();

        train1.setTrainType(TrainType.PASS);
        train1.setLength(1);
        train1.setSpeed(3);

        train2.setTrainType(TrainType.CARGO);
        train2.setLength(2);
        train1.setSpeed(2);

        train3.setTrainType(TrainType.PASSENGER);
        train3.setLength(2);
        train3.setSpeed(2);

        trainRepository.saveAll(List.of(train1,train2,train3));

        TimetableEntry entry1 = new TimetableEntry();
        TimetableEntry entry2 = new TimetableEntry();
        TimetableEntry entry3 = new TimetableEntry();

        entry1.setInputNode(startNode);
        entry1.setEndNode(finishNode);
        entry1.setArrivalTime(LocalDateTime.of(date, LocalTime.of(3,22)));
        entry1.setTrain(train1);
        entry1.setStationingTime(0);

        entry2.setInputNode(startNode);
        entry2.setEndNode(finishNode);
        entry2.setArrivalTime(LocalDateTime.of(date, LocalTime.of(10,50)));
        entry2.setTrain(train2);
        entry2.setStationingTime(40);

        entry3.setInputNode(startNode);
        entry3.setEndNode(finishNode);
        entry3.setArrivalTime(LocalDateTime.of(date, LocalTime.of(22,31)));
        entry3.setTrain(train3);
        entry3.setStationingTime(20);

        entryRepository.saveAll(List.of(entry1, entry2, entry3));
    }

    @GetMapping("/test-mapping")
    public List<TimetableEntryDto> getTimetableEntries() {
        List<TimetableEntry> entryList = entryRepository.findAll();
        return entryList.stream()
                .map(entryMapper::timetableEntryDtoFromTimetableEntry)
                .toList();
    }
}
