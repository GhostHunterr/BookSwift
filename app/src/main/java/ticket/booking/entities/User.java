package ticket.booking.entities;

import java.util.*;

public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    //GETTERS
    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
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
}
