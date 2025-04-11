package az.academy.turing.serviceImpl;

import az.academy.turing.dao.FlightDao;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.model.Flight;
import az.academy.turing.service.FlightService;

import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightDao flightDao;

    public FlightServiceImpl(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public void saveFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setFrom_city(flightDto.getFrom_city());
        flight.setTo_city(flightDto.getFrom_city());
        flight.setTimestamp(flightDto.getTimestamp());
        flight.setAvailable_seats(flightDto.getAvailable_seats());
        flightDao.saveFlight(flight);
    }

    @Override
    public List<FlightDto> findAll() {
        List<Flight> flightList = flightDao.findAll();
        return flightList.stream().map(f -> new FlightDto(f.getId(), f.getFrom_city(), f.getTo_city(), f.getTimestamp(),
                f.getAvailable_seats())).collect(Collectors.toList());

    }

    @Override
    public FlightDto findById(int id) {
        Flight flight = flightDao.findById(id);
        if (flight == null) {
            return null;
        }
        return new FlightDto(flight.getId(), flight.getFrom_city(), flight.getTo_city(),
                flight.getTimestamp(), flight.getAvailable_seats());
    }

    @Override
    public void deleteFlight(int id) {
        flightDao.deleteFlight(id);
    }

    @Override
    public void updateFlightInfo(int id, FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setFrom_city(flightDto.getFrom_city());
        flight.setTo_city(flightDto.getTo_city());
        flight.setTimestamp(flightDto.getTimestamp());
        flight.setAvailable_seats(flightDto.getAvailable_seats());
        flightDao.updateFlightInfo(id,flight);
    }
}
