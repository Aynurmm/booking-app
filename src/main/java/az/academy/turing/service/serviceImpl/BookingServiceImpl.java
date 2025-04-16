package az.academy.turing.service.serviceImpl;

import az.academy.turing.dao.BookingDao;
import az.academy.turing.dao.FlightDao;
import az.academy.turing.dto.BookingDto;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.exception.BookingNotFoundException;
import az.academy.turing.exception.FlightNotFoundException;
import az.academy.turing.exception.InsufficientSeatsException;
import az.academy.turing.model.Booking;
import az.academy.turing.model.Flight;
import az.academy.turing.model.Passenger;
import az.academy.turing.service.BookingService;
import az.academy.turing.service.FlightService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private FlightDao flightDao;
    private  FlightService flightService;

    public BookingServiceImpl(BookingDao bookingDao, FlightDao flightDao, FlightService flightService) {
        this.bookingDao = bookingDao;
        this.flightDao = flightDao;
        this.flightService = flightService;
    }
    public BookingServiceImpl(BookingDao bookingDao){
        this.bookingDao=bookingDao;
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
        return bookingList.stream().map(b -> new BookingDto(b.getId(), b.getFlight_id(), b.getPassenger_id(),b.getNumberOfSeats()))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto findById(int id) {
        Booking booking = bookingDao.findById(id);
        if (booking == null) {
            return null;
        }
        return new BookingDto(booking.getId(), booking.getFlight_id(), booking.getPassenger_id(),booking.getNumberOfSeats());
    }

    @Override
    public void deleteBooking(int id) {
        bookingDao.deleteBooking(id);
    }

    @Override
    public void updateBookingInfo(int id, BookingDto bookingDto) {
        Booking existingbooking=bookingDao.findById(id);
        if(existingbooking==null){
            throw new BookingNotFoundException(id);
        }
        existingbooking.setFlight_id(bookingDto.getFlight_id());
        existingbooking.setPassenger_id(bookingDto.getPassenger_id());
        existingbooking.setNumberOfSeats(bookingDto.getNumberOfSeats());
        bookingDao.updateBooking(id, existingbooking);
    }

    @Override
    public void createBooking(int flightId, int passengerId, int numberOfSeats) {
        Flight flight = flightDao.findById(flightId);
        if (flight == null) {
            throw new FlightNotFoundException(flightId);
        }
        if (!flightService.hasAvailableSeats(flightId, numberOfSeats)) {
            throw new InsufficientSeatsException(flightId, numberOfSeats);
        }

        Booking booking = new Booking(flightId, passengerId, numberOfSeats);
        bookingDao.saveBooking(booking);

        flight.setAvailable_seats(flight.getAvailable_seats() - numberOfSeats);
        flightDao.updateFlightInfo(flightId, flight);
    }

    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookingDao.findById(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException(bookingId);
        }
        Flight flight = flightDao.findById(booking.getFlight_id());
        if (flight != null) {
            flight.setAvailable_seats(flight.getAvailable_seats() + booking.getNumberOfSeats());
            flightDao.updateFlightInfo(flight.getId(), flight);
        }
        bookingDao.deleteBooking(bookingId);
    }

    @Override
    public List<BookingDto> getBookingByPassenger(Passenger passenger) {
        List<Booking>bookingList=bookingDao.findAll();
        List<BookingDto>bookingDtoList=bookingList.stream()
                .filter(b->b.getPassenger_id()==passenger.getId())
                .map(b->{
                        BookingDto bookingDto=new BookingDto();
                        bookingDto.setId(b.getId());
                        bookingDto.setFlight_id(b.getFlight_id());
                        bookingDto.setPassenger_id(b.getPassenger_id());
                        bookingDto.setNumberOfSeats(b.getNumberOfSeats());
                        return bookingDto;

                })
                .collect(Collectors.toList());
        return bookingDtoList;

    }

    @Override
    public boolean hasAvailableSeats(int flightId, int requiredSeats) {
        Flight flight=flightDao.findById(flightId);
        return flight!=null&&flight.getAvailable_seats()>=requiredSeats;
    }


}
