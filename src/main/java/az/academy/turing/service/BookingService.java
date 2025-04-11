package az.academy.turing.service;

import az.academy.turing.dto.BookingDto;
import java.util.List;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);                 // Yeni booking-i saxlamaq
    List<BookingDto> findAll();                               // Bütün Booking-ləri tapmaq
    BookingDto findById(int id);                              // ID-yə görə Booking tapmaq
    void deleteBooking(int id);                               // ID-yə görə Booking silmək
    void updateBookingInfo(int id, BookingDto bookingDto);   // Booking-in məlumatlarını yeniləmək
}

