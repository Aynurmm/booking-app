package az.academy.turing.exception;

public class FlightNotFoundException extends AppException {
    public FlightNotFoundException(int flightId) {

        super("Flight Not Found: " + flightId);
    }
}
