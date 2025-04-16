package az.academy.turing.exception;

import java.time.LocalDate;

public class FlightNotAvailableException extends RuntimeException {
    public FlightNotAvailableException(String toCity, LocalDate date) {
        super("No available flights for destination: " + toCity + " on date: " + date);
    }
}
