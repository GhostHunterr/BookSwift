package ticket.booking.entities;

import java.util.*;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;
    private List<String> stations;

    public Train() {
    }

    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stationTimes, List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    //GETTERS
    public String getTrainId() {
        return this.trainId;
    }

    public String getTrainNo() {
        return this.trainNo;
    }

    public List<List<Integer>> getSeats() {
        return this.seats;
    }

    public List<String> getStations() {
        return this.stations;
    }

    public Map<String, String> getStationTimes() {
        return this.stationTimes;
    }

    public String getTrainInfo() {
        return String.format("Train ID: %s, Train No: %s", trainId, trainNo);
    }

    //SETTERS
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

}
