package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.*;

import java.io.*;
import java.util.*;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    //Local Db path
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/localDb/users.json";


    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);
        System.out.println(users);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(u -> {
            return u.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), u.getPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
}
