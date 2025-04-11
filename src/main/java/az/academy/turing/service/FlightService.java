package az.academy.turing.service;

import az.academy.turing.dao.FlightDao;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.model.Flight;

import java.util.List;

public interface FlightService {
    void saveFlight(FlightDto flightDto);
    List<FlightDto> findAll();
    FlightDto findById(int id);
    void deleteFlight(int id);
    void updateFlightInfo(int id, FlightDto flightDto);
}
