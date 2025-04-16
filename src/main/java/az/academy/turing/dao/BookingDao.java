package az.academy.turing.dao;

import az.academy.turing.model.Booking;

import java.util.List;

public interface BookingDao {
    void saveBooking(Booking booking);
    Booking findById(int id);

    void deleteBooking(int id);
    void updateBooking(int id, Booking booking);

    List<Booking> findAll();
}
