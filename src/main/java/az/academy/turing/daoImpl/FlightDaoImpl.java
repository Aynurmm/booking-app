package az.academy.turing.daoImpl;

import az.academy.turing.config.ConfigHelper;
import az.academy.turing.dao.FlightDao;
import az.academy.turing.enums.FlightQuery;
import az.academy.turing.helper.LoggerHelper;
import az.academy.turing.model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    @Override
    public void saveFlight(Flight flight) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FlightQuery.insert_flight.getQuery());
            preparedStatement.setInt(1, flight.getId());
            preparedStatement.setString(2, flight.getFrom_city());
            preparedStatement.setString(3, flight.getTo_city());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flight.getTimestamp()));
            preparedStatement.setInt(5, flight.getAvailable_seats());
            preparedStatement.executeUpdate();
            LoggerHelper.info("flight saved successfully");

        } catch (SQLException e) {
            LoggerHelper.error("error saving flight");
        }
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flightList = new ArrayList<>();
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FlightQuery.get_allFlights.getQuery());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getInt("id"));
                flight.setTo_city(resultSet.getString("to_city"));
                flight.setFrom_city(resultSet.getString("from_city"));
                flight.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
                flight.setAvailable_seats(resultSet.getInt("available_seats"));
                flightList.add(flight);
            }
            LoggerHelper.info("flights fetched successfully");
        } catch (SQLException e) {
            LoggerHelper.error("error while fetching flights");
        }
        return flightList;
    }

    @Override
    public Flight findById(int id) {
        Flight flight = new Flight();
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FlightQuery.get_flightByIdd.getQuery());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flight.setId(resultSet.getInt("id"));
                flight.setFrom_city(resultSet.getString("from_city"));
                flight.setTo_city(resultSet.getString("to_city"));
                flight.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
                flight.setAvailable_seats(resultSet.getInt("available_seats"));
            }
            LoggerHelper.info("flight fetched by id successfully");
        } catch (SQLException e) {
            LoggerHelper.error("unable to fetch flight by id: " + id);
        }
        return flight;

    }

    @Override
    public void deleteFlight(int id) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FlightQuery.delete_flightById.getQuery());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LoggerHelper.info("flight deleted successfully");
        } catch (SQLException e) {
            LoggerHelper.error("unable to delete flight with id: " + id);
        }
    }

    @Override
    public void updateFlightInfo(int id, Flight flight) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FlightQuery.update_flightById.getQuery());
            preparedStatement.setString(1, flight.getFrom_city());
            preparedStatement.setString(2, flight.getTo_city());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(flight.getTimestamp()));
            preparedStatement.setInt(4, flight.getAvailable_seats());
            preparedStatement.setInt(5, id);
            int rows = preparedStatement.executeUpdate();
            LoggerHelper.info("updated rows count: " + rows);
            LoggerHelper.info("flight info updated successfully");

        } catch (SQLException e) {
            LoggerHelper.error("error updating hotel info: " + e.getMessage());
        }
    }
}
