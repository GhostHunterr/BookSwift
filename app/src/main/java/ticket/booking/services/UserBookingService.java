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
    private static final String USERS_PATH = "./app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();

    }

    public boolean loginUser(String userName, String password) {
        Optional<User> foundUser = userList.stream().filter(u -> u.getName().equals(userName) && UserServiceUtil.checkPassword(password, u.getHashedPassword())).findFirst();
        foundUser.ifPresent(u -> user = u);
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

    public void logoutUser() {
        if (user.isPresent()) {
            user = null;
        }
    }

    public void fetchBookings() {
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId) {
        //Get the List

        try {
            int idx = userList.indexOf(user);

            List<Ticket> bookings = user.getTicketsBooked();
            //Remove
            boolean removed = bookings.removeIf(e -> e.getTicketId().equals(ticketId));

            //Verify & Save
            if (removed && bookings.isEmpty()) {
                user.setTicketsBooked(Collections.emptyList());
            } else {
                user.setTicketsBooked(bookings);
            }
            userList.set(idx, user);
            saveUserListToFile();

            return removed;
        } catch (IOException e) {
            return false;
        }

    }

    public Boolean bookTrainSeat(Train train, int row, int col, String source, String destination) throws IOException {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            int idx = userList.indexOf(user);
            if (seats.get(row).get(col) == 0) {
                seats.get(row).set(col, 1);

                user.getTicketsBooked().add(new Ticket(UUID.randomUUID().toString(), user.getUserId(), source, destination, train.getStationTimes().get(source)));
                userList.set(idx, user);
                saveUserListToFile();

                trainService.updateTrain(train);
                return true;
            } else {
                System.out.println("This seat is already booked.");
                return false;
            }
        } catch (IOException e) {
            throw e;
        }
    }


    //OTHER

    public List<Train> getTrains(String source, String destination) throws IOException {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException e) {
            throw e;
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

    public boolean isLoggedIn() {
        return (user != null && user.isPresent());
    }

    public String getCurrentUserInfo() {
        return String.format("USER NAME: %s", user.getName());
    }


}
