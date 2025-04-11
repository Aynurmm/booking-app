package az.academy.turing.serviceImpl;

import az.academy.turing.dao.BookingDao;
import az.academy.turing.dto.BookingDto;
import az.academy.turing.model.Booking;
import az.academy.turing.service.BookingService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;

    public BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public void saveBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setId(bookingDto.getId());
        booking.setFlight_id(bookingDto.getFlight_id());
        booking.setPassenger_id(bookingDto.getPassenger_id());
        bookingDao.saveBooking(booking);
    }

    @Override
    public List<BookingDto> findAll() {
        List<Booking> bookingList = bookingDao.findAll();
        return bookingList.stream().map(b -> new BookingDto(b.getId(), b.getFlight_id(), b.getPassenger_id()))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto findById(int id) {
        Booking booking = bookingDao.findById(id);
        if (booking == null) {
            return null;
        }
        return new BookingDto(booking.getId(), booking.getFlight_id(), booking.getPassenger_id());
    }

    @Override
    public void deleteBooking(int id) {
        bookingDao.deleteBooking(id);
    }

    @Override
    public void updateBookingInfo(int id, BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setFlight_id(bookingDto.getFlight_id());
        booking.setPassenger_id(bookingDto.getPassenger_id());
        bookingDao.updateBooking(id, booking);
    }
}
