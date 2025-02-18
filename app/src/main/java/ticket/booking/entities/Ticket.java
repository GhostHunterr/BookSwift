package ticket.booking.entities;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateOfTravel;

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
    }

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User &s from %s to %s on %s", ticketId, userId, source, destination, dateOfTravel);
    }

    //GETTERS
    String getTicketId() {
        return this.ticketId;
    }

    String getUserId() {
        return this.userId;
    }

    String getSource() {
        return this.source;
    }

    String getDestination() {
        return this.destination;
    }

    String getDateOfTravel() {
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
