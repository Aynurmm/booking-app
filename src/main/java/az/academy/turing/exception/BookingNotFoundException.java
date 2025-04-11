package az.academy.turing.exception;

public class BookingNotFoundException extends AppException {
    public BookingNotFoundException(int bookingId) {

        super(" Booking Not Found: " + bookingId);
    }
}
