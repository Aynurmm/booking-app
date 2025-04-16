package az.academy.turing.controller;

import az.academy.turing.dto.BookingDto;
import az.academy.turing.model.Passenger;
import az.academy.turing.service.BookingService;

import java.util.List;

public class BookingController implements BookingService {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingController() {

    }

    @Override
    public void saveBooking(BookingDto bookingDto) {
        bookingService.saveBooking(bookingDto);
    }

    @Override
    public List<BookingDto> findAll() {
        return bookingService.findAll();
    }

    @Override
    public BookingDto findById(int id) {
        return bookingService.findById(id);
    }

    @Override
    public void deleteBooking(int id) {
        bookingService.deleteBooking(id);
    }

    @Override
    public void updateBookingInfo(int id, BookingDto bookingDto) {
        bookingService.updateBookingInfo(id, bookingDto);
    }

    @Override
    public void createBooking(int flightId, int passengerId, int numberOfSeats) {
        bookingService.createBooking(flightId, passengerId, numberOfSeats);
    }

    @Override
    public void cancelBooking(int bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDto> getBookingByPassenger(Passenger passenger) {
        return bookingService.getBookingByPassenger(passenger);
    }

    @Override
    public boolean hasAvailableSeats(int flightId, int requiredSeats) {
        return bookingService.hasAvailableSeats(flightId, requiredSeats);
    }
}

