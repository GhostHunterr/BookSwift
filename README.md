# Ticket Booking System

A Java-based ticket booking system that allows users to log in, book tickets, cancel bookings, and more. The project uses Gradle for build automation, Jackson for JSON processing, and BCrypt for secure password hashing.

## Features

- **User Authentication:**  
  - Login and logout functionality.
  - Secure password handling using BCrypt.
  
- **Ticket Management:**  
  - Book and cancel tickets.
  - Persistence of user and ticket data using JSON files.
  
- **JSON Processing:**  
  - Data serialization and deserialization with Jackson.
  - Configurable naming strategies for seamless JSON mapping.

## Technologies Used

- **Java:** Core application logic.
- **Gradle:** Build automation and dependency management.
- **Jackson:** JSON parsing and generation.
- **BCrypt:** Password hashing (using jBCrypt or Spring Security's BCryptPasswordEncoder).
- **Git:** Version control.

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Git
- (Optional) Gradle installed globally, though the Gradle Wrapper is provided

### Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/ticket-booking.git
   cd ticket-booking
   ```

2. **Build the Project:**

   Use the Gradle wrapper to build the project:
   
   ```bash
   ./gradlew :app:build
   ```

3.  **Running the Application**

    ```bash
     ./gradlew :app:run
    ```

## Project Structure

- **`ticket.booking.entities`**: Contains domain models such as `User`, `Ticket`, and `Train`.
- **`ticket.booking.services`**: Contains service classes (e.g., `UserBookingService`) for handling business logic.
- **`ticket.booking.localDb`**: Contains Dummy DataBase for testing.
- **`ticket.booking.util`**: Contains Utility methods.
- **`build.gradle`**: Gradle build configuration.

## Configuration

- **JSON Persistence:**  
  User data is stored in a JSON file (configured in your code as `USERS_PATH`).  
- **Property Naming Strategy:**  
  The project is configured to use `PropertyNamingStrategies.SNAKE_CASE` for Jackson to map JSON snake_case properties (e.g., `hashed_password`) to Java camelCase fields (e.g., `hashedPassword`).


## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature-name`).
3. Commit your changes.
4. Push your branch to your fork.
5. Create a pull request describing your changes.

Please ensure your code adheres to the project’s style guidelines and that all tests pass before submitting a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
