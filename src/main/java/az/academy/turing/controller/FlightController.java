package az.academy.turing.controller;

import az.academy.turing.dto.FlightDto;
import az.academy.turing.service.FlightService;

import java.util.List;

public class FlightController implements FlightService {
    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
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
}
