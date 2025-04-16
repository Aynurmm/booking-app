package az.academy.turing.service;

import az.academy.turing.dao.FlightDao;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.model.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface FlightService {
    void saveFlight(FlightDto flightDto);
    List<FlightDto> findAll();
    FlightDto findById(int id);
    void deleteFlight(int id);
    void updateFlightInfo(int id, FlightDto flightDto);

    List<FlightDto>getFlightsInNext24Hours();
    List<FlightDto>searchFlights(String toCity, LocalDate date, int numberOfPassenger);
    boolean hasAvailableSeats(int flightId, int requiredSeats);

}
