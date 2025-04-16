package az.academy.turing.exception;

public class InsufficientSeatsException extends RuntimeException {
    public InsufficientSeatsException(int flightId, int numberOfSeats) {
        super("Not enough available seats for flight ID: "+flightId);
    }
}
