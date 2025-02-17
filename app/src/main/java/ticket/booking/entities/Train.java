package ticket.booking.entities;

import java.sql.Time;
import java.util.*;

public class Train {
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats;
    private Map<String, Time> stationTimes;
    private List<String> stations;
}
