package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.*;
import ticket.booking.util.*;

import java.io.*;
import java.util.*;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    //Local Db path
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();

    }

    public boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(u -> u.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), u.getHashedPassword())).findFirst();
        return foundUser.isPresent();
    }

    public boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void fetchBookings() {
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId) {
        //Get the List
        try {
            List<Ticket> bookings = user.getTicketsBooked();

            //Remove
            boolean removed = bookings.removeIf(e -> e.getTicketId().equals(ticketId));

            //Verify & Save
            if (removed && bookings.isEmpty()) {
                user.setTicketsBooked(Collections.emptyList());
            } else {
                user.setTicketsBooked(bookings);
            }
            saveUserListToFile();

            return removed;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    //Helper Functions;
    public void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }
}
