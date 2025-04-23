package az.academy.turing.console;

import az.academy.turing.controller.BookingController;
import az.academy.turing.controller.FlightController;
import az.academy.turing.controller.PassengerController;
import az.academy.turing.dao.BookingDao;
import az.academy.turing.dao.FlightDao;
import az.academy.turing.dao.PassengerDao;
import az.academy.turing.dao.daoImpl.BookingDaoImpl;
import az.academy.turing.dao.daoImpl.FlightDaoImpl;
import az.academy.turing.dao.daoImpl.PassengerDaoImpl;
import az.academy.turing.dto.BookingDto;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.exception.MenuOptionNotFoundException;
import az.academy.turing.model.Passenger;
import az.academy.turing.service.BookingService;
import az.academy.turing.service.FlightService;
import az.academy.turing.service.PassengerService;
import az.academy.turing.service.serviceImpl.BookingServiceImpl;
import az.academy.turing.service.serviceImpl.FlightServiceImpl;
import az.academy.turing.service.serviceImpl.PassengerServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    Scanner scanner=new Scanner(System.in);
    FlightDao flightDao = new FlightDaoImpl();
    FlightService flightService = new FlightServiceImpl(flightDao);
    FlightController flightController = new FlightController(flightService);

    BookingDao bookingDao = new BookingDaoImpl();
    BookingService bookingService = new BookingServiceImpl(bookingDao);
    BookingController bookingController = new BookingController(bookingService);

    PassengerDao passengerDao = new PassengerDaoImpl();
    PassengerService passengerService = new PassengerServiceImpl(passengerDao);
    PassengerController passengerController = new PassengerController(passengerService);


    Passenger curPassenger;

    public ConsoleApp(FlightController flightController, BookingController bookingController, PassengerController passengerController) {
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.passengerController = passengerController;
        this.scanner = new Scanner(System.in);

    }

    public ConsoleApp() {

    }

    public void start() {
        while (true) {
            printMenu();
            int choice = getIntInput("Choose an option:");

            try {
                handleMenuOption(choice);
            } catch (MenuOptionNotFoundException e) {
                System.out.println("Menu error: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private void printMenu() {

        System.out.println("---------------------Flight Booking System-------------------");
        System.out.println("1. Online-board\n2.Show flight info\n3.Search and book a flight" +
                "\n4.Cancel the booking\n5.My flights\n6.Exit");

    }

    private void showOnlineBoard() {
        try {
            List<FlightDto> flights = flightService.getFlightsInNext24Hours();
            for (FlightDto flight : flights) {
                System.out.println(flight);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching flights: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showFlightInfo() {
        int id = getIntInput("Enter flight ID:");
        FlightDto flight = flightService.findById(id);
        System.out.println(flight);
    }

    private void searchAndBookFlight() {
        String destination = getStringInput("Enter destination:");
        LocalDate date = LocalDate.parse(getStringInput("Enter date (yyyy-mm-dd):"));
        int numberOfSeats = getIntInput("Enter number of tickets:");

        List<FlightDto> flights = flightService.searchFlights(destination, date, numberOfSeats);
        if (flights.isEmpty()) {
            System.out.println("No flights found.");
            return;
        }

        System.out.println("Available flights:");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i));
        }

        int choice = getIntInput("Choose flight (0 to cancel):");
        if (choice == 0) return;

        FlightDto selectedFlight = flights.get(choice - 1);
        int passengerId = getIntInput("Enter your passenger ID:");

        for (int i = 0; i < numberOfSeats; i++) {
            String name = getStringInput("Enter passenger full name for seat " + (i + 1) + ":");
        }

        bookingService.createBooking(selectedFlight.getId(), passengerId, numberOfSeats);
        System.out.println("Booking successful!");
    }

    private void cancelBooking() {
        int id = getIntInput("Enter booking ID:");
        bookingService.cancelBooking(id);
        System.out.println("Booking cancelled.");
    }

    private void viewMyFlights() {
        int id = getIntInput("Enter your id:");
        Passenger passenger = new Passenger();
        passenger.setId(id);
        List<BookingDto> bookings = bookingService.getBookingByPassenger(passenger);
        for (BookingDto booking : bookings) {
            System.out.println(booking);
        }
    }

    private int getIntInput(String message) {
        System.out.print(message + " ");
        return Integer.parseInt(scanner.nextLine());
    }

    private String getStringInput(String message) {
        System.out.print(message + " ");
        return scanner.nextLine();
    }


    private void handleMenuOption(int option) throws MenuOptionNotFoundException {
        switch (option) {
            case 1 -> showOnlineBoard();
            case 2 -> showFlightInfo();
            case 3 -> searchAndBookFlight();
            case 4 -> cancelBooking();
            case 5 -> viewMyFlights();
            case 6 -> {
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
    }


}



