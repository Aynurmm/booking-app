package az.academy.turing.service.serviceImpl;

import az.academy.turing.dao.FlightDao;
import az.academy.turing.dto.FlightDto;
import az.academy.turing.exception.FlightNotAvailableException;
import az.academy.turing.exception.FlightNotFoundException;
import az.academy.turing.model.Flight;
import az.academy.turing.service.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
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
        flightDao.updateFlightInfo(id, flight);
    }

    @Override
    public List<FlightDto> getFlightsInNext24Hours() {
        List<Flight> allFlight = flightDao.findAll();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusHours(24);

        List<FlightDto> filteredFlights = allFlight.stream()
                .filter(f -> {
                    LocalDateTime flightTime = f.getTimestamp();
                    return !flightTime.isBefore(now) && !flightTime.isAfter(next24Hours);
                })
                .map(f ->
                        new FlightDto(f.getId(), f.getFrom_city(), f.getTo_city(), f.getTimestamp(),
                                f.getAvailable_seats())
                )
                .collect(Collectors.toList());

        return filteredFlights;
    }

    @Override
    public List<FlightDto> searchFlights(String toCity, LocalDate date, int numberOfPassenger) {
        List<Flight> allflight = flightDao.findAll();
        List<FlightDto> filteredFlight = allflight.stream()
                .filter(f ->
                        f.getTo_city().equals(toCity) &&
                                f.getTimestamp().toLocalDate().equals(date) &&
                                f.getAvailable_seats() >= numberOfPassenger)
                .map(f -> new FlightDto(f.getId(), f.getFrom_city(), f.getTo_city(), f.getTimestamp(), f.getAvailable_seats()))
                .collect(Collectors.toList());
        if (filteredFlight.isEmpty()) {
            throw new FlightNotAvailableException(toCity,date);
        }
        return filteredFlight;

    }

    @Override
    public boolean hasAvailableSeats(int flightId, int requiredSeats) {
        Flight flight = flightDao.findById(flightId);
        if (flight==null){
            throw new FlightNotFoundException(flightId);
        }
        return flight.getAvailable_seats()>=requiredSeats;
    }
    }
