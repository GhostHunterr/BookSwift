/*
 * This source file was generated by the Gradle 'init' task
 */
package ticket.booking;

import ticket.booking.services.UserBookingService;
import ticket.booking.entities.*;
import ticket.booking.util.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System.");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to Load Users.");
            return;
        }

        while (option != 3) {
            //Options for New User
            if (!userBookingService.isLoggedIn()) {
                System.out.println("Choose Option");
                System.out.println("1. Sign Up");
                System.out.println("2. Login");
                System.out.println("3. Exit the App");
                option = scanner.nextInt();

                switch (option) {
                    case 1: {
                        System.out.println("Enter the username to signup");
                        String nameToSignup = scanner.next();
                        System.out.println("Enter the password to signup");
                        String passwordToSignUp = scanner.next();
                        User newUser = new User(nameToSignup, UserServiceUtil.hashPassword(passwordToSignUp), Collections.emptyList(), UUID.randomUUID().toString());
                        System.out.println(userBookingService.signUp(newUser) ? "Registered Successfully" : "Failed to Register User :(\n Try again!");
                        break;
                    }
                    case 2: {
                        System.out.println("Enter the username to login");
                        String nameToLogin = scanner.next();
                        System.out.println("Enter the password to login");
                        String passwordToLogin = scanner.next();
                        System.out.println(userBookingService.loginUser(nameToLogin, passwordToLogin) ? "Logged In Successfully" : "Failed to Login User :(\n Try again!");
                        break;
                    }
                }
            } else {
                //Options for Logged In User.
                System.out.println("Currently Logged In as \n" + userBookingService.getCurrentUserInfo());
                System.out.println("1. Fetch Bookings");
                System.out.println("2. Search Trains");
                System.out.println("3. Book a seat");
                System.out.println("4. Cancel my Booking");
                System.out.println("5. Log Out");
                option = scanner.nextInt();

                switch (option) {
                    case 1: {
                        System.out.println("Fetching your Bookings...");
                        userBookingService.fetchBookings();
                        break;
                    }
                    case 2, 3: {
                        System.out.println("Enter your source Station.");
                        String source = scanner.next();
                        System.out.println("Enter your Destination Station.");
                        String destination = scanner.next();
                        try {
                            List<Train> availableTrains = userBookingService.getTrains(source, destination);
                            // Print the trains;
                            if (availableTrains.isEmpty()) {
                                System.out.println("No Trains Found!");
                            } else {
                                System.out.println("-----Available Trains-----");
                                int index = 0;
                                for (Train t : availableTrains) {
                                    System.out.println(index++ + ") " + t.getTrainInfo());
                                    for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                                        System.out.println(" --> station " + entry.getKey() + " time: " + entry.getValue());
                                    }
                                }
                                System.out.println("--------That's it----------");

                                //To book Seats
                                if (option == 3) {
                                    System.out.println("Enter the Index for the train you want to book. Like 0, 1, 2...");
                                    Train trainSelectedForBooking = availableTrains.get(scanner.nextInt());
                                    System.out.println("Select a seat out of these seats. Occupied ones are marked as \"O\" and Empty one with \"_\"");
                                    List<List<Integer>> seats = trainSelectedForBooking.getSeats();
                                    int r = 0, c = 0;
                                    for (List<Integer> row : seats) {
                                        System.out.print("Rows: " + r++ + " [");
                                        for (Integer val : row) {
                                            System.out.print(val == 0 ? "(" + c++ + ")" + "_," : "(" + c++ + ")" + "O,");
                                        }
                                        System.out.print("]");

                                        System.out.println();
                                        c = 0;
                                    }

                                    boolean valid = false;

                                    do {
                                        System.out.println("Select the seat by typing the row and column");
                                        System.out.println("Enter the row");
                                        int row = scanner.nextInt();
                                        System.out.println("Enter the column");
                                        int col = scanner.nextInt();
                                        if ((0 <= row && row < seats.size()) && (0 <= col && col < seats.get(0).size())) {
                                            System.out.println("Booking your seat....");
                                            try {
                                                Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                                                valid = booked;
                                            } catch (IOException e) {
                                                System.out.println("Error Booking Ticket.\nPlease Try Again.");
                                            }
                                        } else {
                                            System.out.println("Enter valid rows and columns.");
                                        }
                                    } while (!valid);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Couldn't Fetch the List.");
                        }

                        break;
                    }
                    case 4: {

                    }
                    case 5: {
                        userBookingService.logoutUser();
                        System.out.println("User has been successfully Logged Out.");
                        break;
                    }
                }
                option = 0;
            }
        }

        System.out.println("Exiting the App.\nGood Bye.");
    }
}
