package az.academy.turing.dao;

import az.academy.turing.model.Flight;

import java.util.List;

public interface FlightDao {
    void saveFlight(Flight flight);

    List<Flight> findAll();
    Flight findById(int id);
    void deleteFlight(int id);
    void updateFlightInfo(int id, Flight flight);
}
