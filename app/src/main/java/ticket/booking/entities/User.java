package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User() {
    }

    public User(String name, String hashedPassword, List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    //GETTERS
    public String getName() {
        return this.name;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return this.ticketsBooked;
    }

    public void printTickets() {
        for (Ticket t : ticketsBooked) {
            System.out.println(t.getTicketInfo());
        }
    }

    public String getUserId() {
        return this.userId;
    }


    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //Helper Functions
    public boolean isPresent() {
        return (userId != null && !userId.trim().isEmpty());
    }
}
