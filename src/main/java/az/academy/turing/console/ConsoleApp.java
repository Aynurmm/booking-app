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
import az.academy.turing.exception.MenuOptionNotFoundException;
import az.academy.turing.model.Passenger;
import az.academy.turing.service.BookingService;
import az.academy.turing.service.FlightService;
import az.academy.turing.service.PassengerService;
import az.academy.turing.service.serviceImpl.BookingServiceImpl;
import az.academy.turing.service.serviceImpl.FlightServiceImpl;
import az.academy.turing.service.serviceImpl.PassengerServiceImpl;

import java.util.Scanner;

public class ConsoleApp {

    Scanner scanner;
    FlightDao flightDao=new FlightDaoImpl();
    FlightService flightService=new FlightServiceImpl(flightDao);
    FlightController flightController=new FlightController(flightService);

    BookingDao bookingDao=new BookingDaoImpl();
    BookingService bookingService=new BookingServiceImpl(bookingDao);
    BookingController bookingController=new BookingController(bookingService);

    PassengerDao passengerDao=new PassengerDaoImpl();
    PassengerService passengerService=new PassengerServiceImpl(passengerDao);
    PassengerController passengerController=new PassengerController(passengerService);




    Passenger curPassenger;

    public ConsoleApp(FlightController flightController, BookingController bookingController, PassengerController passengerController) {
        this.flightController = flightController;
        this.bookingController = bookingController;
        this.passengerController = passengerController;
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
                break;
            case 4:
                System.out.println("Enter booking Id:");
                String bookingId = scanner.nextLine().trim();
                break;
            case 5:
                System.out.println("Enter your name and surname:");
                String nameSurname = scanner.nextLine().trim();
                break;
            case 6:
                System.out.println("Exit from Application and SAVING your data!");
                System.exit(0);
                break;
            default:
                throw new MenuOptionNotFoundException("Invalid menu number.");
        }
    }


}



