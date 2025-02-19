package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {

    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String TRAIN_PATH = "app/src/main/java/ticket/booking/localDb/trains.json";

    public TrainService() throws IOException {
        loadTrains();
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream().filter(t -> validTrain(t, source, destination)).collect(Collectors.toList());
    }

    private boolean validTrain(Train t, String source, String destination) {
        List<String> stations = t.getStations().stream().map(e -> e.toLowerCase()).toList();

        int sourceIndex = stations.indexOf(source.toLowerCase());
        int destinationIndex = stations.indexOf(destination.toLowerCase());

        return (sourceIndex != -1 && destinationIndex != -1) && (sourceIndex < destinationIndex);
    }

    public void updateTrain(Train t) throws IOException {
        OptionalInt foundTrainInd = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(t.getTrainId()))
                .findFirst();

        if (foundTrainInd.isPresent()) {
            trainList.set(foundTrainInd.getAsInt(), t);
            saveTrainListToFile();
        }
    }

    //Helper

    public void loadTrains() throws IOException {
        File trains = new File(TRAIN_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public void saveTrainListToFile() throws IOException {
        File trains = new File(TRAIN_PATH);
        objectMapper.writeValue(trains, trainList);
    }
}
