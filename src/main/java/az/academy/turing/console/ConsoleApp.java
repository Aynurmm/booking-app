package az.academy.turing.console;

import az.academy.turing.controller.BookingController;
import az.academy.turing.controller.FlightController;
import az.academy.turing.controller.PassengerController;
import az.academy.turing.controller.SeatsController;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.exception.AppException;
import az.academy.turing.model.Passenger;
import az.academy.turing.dto.BookingDto;

import java.util.Scanner;
import java.util.List;

public class ConsoleApp {

    Scanner scanner = new Scanner(System.in);
    FlightController flightController = new FlightController();
    BookingController bookingController = new BookingController();
    PassengerController passengerController = new PassengerController();
    SeatsController seatsController = new SeatsController();

    Passenger curPassenger;

    public void start() {
        System.out.println("------------------Welcome to Flight Booking App!---------------");
        authentication();
        while (true) {
            try {
                System.out.println("1. Online-board");
                System.out.println("2. Show flight info");
                System.out.println("3. Search and book flight");
                System.out.println("4. Cancel booking");
                System.out.println("5. My flights");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Online-board");
                        break;
                    case 2:
                        // Flights information could be displayed here
                        break;
                    case 3:
                        searchAndBookFlight();
                        break;
                    case 4:
                        cancelBooking();
                        break;
                    case 5:
                        showMyFlights();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        throw new AppException("Invalid selection: " + choice);
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void authentication() {
        while (true) {
            System.out.println("Entrance page:");
            System.out.print("Passenger Name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Password: ");
            String passengerPassword = scanner.nextLine();

            // Validation logic for passenger name and password should be added here
            // For now, we will just create a sample passenger
            curPassenger = new Passenger(); // Create actual passenger logic
            System.out.println("Authentication successful!");
            break;
        }
    }

    private void searchAndBookFlight() {
        System.out.println("Searching for flights...");
        // Example logic for flight searching and reserving
        // Assuming we have a method to get the available flights

        List<FlightDto> availableFlights = flightController.findAll();
        for (FlightDto flight : availableFlights) {
            System.out.println("Flight ID: " + flight.getId() + ", From: " + flight.getFrom_city() + ", To: " + flight.getTo_city());
        }

        System.out.print("Enter flight ID to book: ");
        int flightId = scanner.nextInt();
        // Creating a booking (this is just an example; use actual passenger ID and other details)
        BookingDto newBooking = new BookingDto();
        newBooking.setFlight_id(flightId);
        newBooking.setPassenger_id(curPassenger.getId()); // Assuming getId() method exists
        bookingController.saveBooking(newBooking);

        System.out.println("Booking successful!");
    }

    private void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        bookingController.deleteBooking(bookingId);
        System.out.println("Booking canceled!");
    }

    private void showMyFlights() {
        System.out.println("Showing your flights...");
        // Logic to display the passenger's flights
    }
}

// Note: The Pseudo methods like `getId` in Passenger class, and other validation logic should be defined in the respective classes.
