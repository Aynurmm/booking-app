package az.academy.turing.dao;

import az.academy.turing.model.Passenger;

import java.util.List;

public interface PassengerDao {
    void savePassenger(Passenger passenger);
    void deletePassenger(int id);
    Passenger findById(int id);
    List<Passenger>findAll();
    void updatePassengerInfo(int id,Passenger passenger);


}
