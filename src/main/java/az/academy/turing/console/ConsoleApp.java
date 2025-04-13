package az.academy.turing.console;

import az.academy.turing.controller.BookingController;
import az.academy.turing.controller.FlightController;
import az.academy.turing.controller.PassengerController;
import az.academy.turing.controller.SeatsController;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.exception.AppException;
import az.academy.turing.exception.MenuOptionNotFoundException;
import az.academy.turing.model.Passenger;
import az.academy.turing.dto.BookingDto;

import java.util.Scanner;
import java.util.List;

public class ConsoleApp {

    Scanner scanner;
    FlightController flightController = new FlightController();
    BookingController bookingController = new BookingController();
    PassengerController passengerController = new PassengerController();
    SeatsController seatsController = new SeatsController();

    Passenger curPassenger;

    public ConsoleApp(FlightController flightController, BookingController bookingController, PassengerController passengerController, SeatsController seatsController) {
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.passengerController = passengerController;
        this.seatsController = seatsController;
        this.scanner = new Scanner(System.in);

    }

    public void start() {
        System.out.println("------------------Welcome to Flight Booking App!---------------");
        while (true) {
            printMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());
            } catch (MenuOptionNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void printMenu() {

        System.out.println("---------------------Flight Booking System-------------------");
        System.out.println("1. Online-board\n2.Show flight info\n3.Search and book a flight" +
                "\n4.Cancel the booking\n5.My flights\n6.Exit");

    }

    private void handleMenuOption(int option) throws MenuOptionNotFoundException {
        switch (option) {
            case 1:
                //flightController.showOnlineBoard();
                break;
            case 2:
                System.out.println("Enter Flight Id:");
                String flightId = scanner.nextLine().trim();
                //flightController.showFlightInfo(flightId);
                break;
            case 3:
                System.out.println("Destination:");
                String destination = scanner.nextLine().trim();
                System.out.println("Date(yyyy-MM-dd):");
                String date = scanner.nextLine().trim();
                System.out.println("Passenger number:");
                int passengerNum = Integer.parseInt(scanner.nextLine().trim());
                //  bookingController.searchAndBookingFlight(destination,date,passengerNum,scanner);
                break;
            case 4:
                System.out.println("Enter booking Id:");
                String bookingId = scanner.nextLine().trim();
                //bookingController.cancelBooking(bookingId);
                break;
            case 5:
                System.out.println("Enter your name and surname:");
                String nameSurname = scanner.nextLine().trim();
                //bookingController.showMyFlights(nameSurname);
                break;
            case 6:
                System.out.println("Exit from Application and SAVING your data!");
                flightController.saveFlight();
                bookingController.saveBooking();
                System.exit(0);
                break;
            default:
                throw new MenuOptionNotFoundException("Invalid menu number.");
        }
    }


//    private void authentication() {
//        while (true) {
//            System.out.println("Entrance page:");
//            System.out.print("Passenger Name: ");
//            String passengerName = scanner.nextLine();
//            System.out.print("Password: ");
//            String passengerPassword = scanner.nextLine();
//
//
//            curPassenger = new Passenger();
//            System.out.println("Authentication successful!");
//            break;
//        }
//    }
//
//    private void searchAndBookFlight() {
//        System.out.println("Searching for flights...");
//
//
//        List<FlightDto> availableFlights = flightController.findAll();
//        for (FlightDto flight : availableFlights) {
//            System.out.println("Flight ID: " + flight.getId() + ", From: " + flight.getFrom_city() + ", To: " + flight.getTo_city());
//        }
//
//        System.out.print("Enter flight ID to book: ");
//        int flightId = scanner.nextInt();
//        BookingDto newBooking = new BookingDto();
//        newBooking.setFlight_id(flightId);
//        newBooking.setPassenger_id(curPassenger.getId());
//        bookingController.saveBooking(newBooking);
//
//        System.out.println("Booking successful!");
//    }
//
//    private void cancelBooking() {
//        System.out.print("Enter booking ID to cancel: ");
//        int bookingId = scanner.nextInt();
//        bookingController.deleteBooking(bookingId);
//        System.out.println("Booking canceled!");
//    }
//
//    private void showMyFlights() {
//        System.out.println("Showing your flights");
}
}


