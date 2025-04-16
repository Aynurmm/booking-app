package az.academy.turing.controller;

import az.academy.turing.dto.FlightDto;
import az.academy.turing.service.FlightService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class FlightController implements FlightService {
    private FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public FlightController() {

    }

    @Override
    public void saveFlight(FlightDto flightDto) {
        flightService.saveFlight(flightDto);
    }

    @Override
    public List<FlightDto> findAll() {
        return flightService.findAll();
    }

    @Override
    public FlightDto findById(int id) {
        return flightService.findById(id);
    }

    @Override
    public void deleteFlight(int id) {
        flightService.deleteFlight(id);

    }

    @Override
    public void updateFlightInfo(int id, FlightDto flightDto) {
        flightService.updateFlightInfo(id, flightDto);
    }

    @Override
    public List<FlightDto> getFlightsInNext24Hours() {
        return flightService.getFlightsInNext24Hours();
    }

    @Override
    public List<FlightDto> searchFlights(String toCity, LocalDate date, int numberOfPassenger) {
        return flightService.searchFlights(toCity,date,numberOfPassenger);
    }

    @Override
    public boolean hasAvailableSeats(int flightId, int requiredSeats) {
       return flightService.hasAvailableSeats(flightId,requiredSeats);
    }
}
