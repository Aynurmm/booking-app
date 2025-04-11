package az.academy.turing.console;

import az.academy.turing.controller.BookingController;
import az.academy.turing.controller.FlightController;
import az.academy.turing.controller.PassengerController;
import az.academy.turing.controller.SeatsController;
import az.academy.turing.exception.AppException;
import az.academy.turing.exception.InvalidSelectionException;
import az.academy.turing.model.Passenger;

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
        while (true) {
            try {

            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void authentication(){
        while (true) {
            System.out.println("entrance page:");
            System.out.print("Passenger Name:");
            String passengerName = scanner.nextLine();
            System.out.print("Password:");
            String passengerPassword = scanner.nextLine();

        }
    }



//        while(true){
//
//        System.out.println("""
//                1.Online-board
//                2.Show flight info
//                3.Search and book flight
//                4.Cancel booking
//                5.My flights
//                6.Exit""");
//
//        System.out.println("Enter your choice:");
//        int choice = scanner.nextInt();
//        switch (choice) {
//            case 1:
//                System.out.println("Online-board");
//                break;
//            case 2:
//                System.out.println("Show flight info");
//                break;
//            case 3:
//                System.out.println("Search and book flight");
//                break;
//            case 4:
//                System.out.println("Cancel booking");
//                break;
//            case 5:
//                System.out.println("My flights");
//                break;
//            case 6:
//                System.out.println("Exit");
//                break;
//            default:
//                throw new InvalidSelectionException(choice);
//        }
//    }
}
