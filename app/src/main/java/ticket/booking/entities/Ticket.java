package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateOfTravel;


    public Ticket() {
    }

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
    }

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, source, destination, dateOfTravel);
    }

    //GETTERS
    public String getTicketId() {
        return this.ticketId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDateOfTravel() {
        return this.dateOfTravel;
    }


    //SETTERS
    void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    void setUserId(String userId) {
        this.userId = userId;
    }

    void setSource(String source) {
        this.source = source;
    }

    void setDestination(String destination) {
        this.destination = destination;
    }

    void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

}
