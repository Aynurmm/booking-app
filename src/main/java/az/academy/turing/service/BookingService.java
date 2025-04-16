package az.academy.turing.service;

import az.academy.turing.dto.BookingDto;
import az.academy.turing.model.Passenger;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);
    List<BookingDto> findAll();
    BookingDto findById(int id);
    void deleteBooking(int id);
    void updateBookingInfo(int id, BookingDto bookingDto);

    void createBooking(int flightId, int passengerId, int numberOfSeats);
    void cancelBooking(int bookingId);
    List<BookingDto>getBookingByPassenger(Passenger passenger);
    boolean hasAvailableSeats(int flightId, int requiredSeats);


}

