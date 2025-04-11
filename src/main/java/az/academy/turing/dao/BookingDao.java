package az.academy.turing.dao;

import az.academy.turing.model.Booking;

import java.util.List;

public interface BookingDao {
    void saveBooking(Booking booking);                // Booking-i saxlamaq üçün metod

    Booking findById(int id);                        // ID-yə görə Booking tapmaq üçün metod

    void deleteBooking(int id);                       // ID-yə görə Booking silmək üçün metod

    void updateBooking(int id, Booking booking);     // Məlumatları yeniləmək üçün metod

    List<Booking> findAll();                         // Bütün Bookings-ləri tapmaq üçün metod
}
