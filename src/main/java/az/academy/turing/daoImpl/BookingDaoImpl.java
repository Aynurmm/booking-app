package az.academy.turing.daoImpl;

import az.academy.turing.config.ConfigHelper;
import az.academy.turing.dao.BookingDao;
import az.academy.turing.enums.BookingQuery;
import az.academy.turing.helper.LoggerHelper;
import az.academy.turing.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {


    @Override
    public void saveBooking(Booking booking) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery.insert_booking.getQuery());
            preparedStatement.setInt(1, booking.getId());
            preparedStatement.setInt(2, booking.getFlight_id());
            preparedStatement.setInt(3, booking.getPassenger_id());
            preparedStatement.executeUpdate();
            LoggerHelper.info("Booking saved successfully");
        } catch (SQLException e) {
            LoggerHelper.error("Error saving booking: " + e.getMessage());
        }
    }

    @Override
    public Booking findById(int id) {
        Booking booking = null;
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery.get_bookingById.getQuery());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                booking = new Booking(resultSet.getInt("id"), resultSet.getInt("flight_id"), resultSet.getInt("passenger_id"));
            }
            LoggerHelper.info("Booking fetched by id successfully");

        } catch (
                SQLException e) {
            LoggerHelper.error("Unable to fetch booking by id: " + e.getMessage());
        }
        return booking;
    }

    @Override
    public void deleteBooking(int id) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery.delete_bookingById.getQuery());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LoggerHelper.info("Booking deleted successfully");
        } catch (SQLException e) {
            LoggerHelper.error("Unable to delete booking with id: " + e.getMessage());
        }
    }

    @Override
    public void updateBooking(int id, Booking booking) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery.update_bookingById.getQuery());
            preparedStatement.setInt(1, booking.getFlight_id());
            preparedStatement.setInt(2, booking.getPassenger_id());
            preparedStatement.setInt(3, id);
            int rows = preparedStatement.executeUpdate();
            LoggerHelper.info("Updated rows count: " + rows);
            LoggerHelper.info("Booking info updated successfully");
        } catch (SQLException e) {
            LoggerHelper.error("Error updating booking info: " + e.getMessage());
        }
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookingList = new ArrayList<>();
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(BookingQuery.get_allBookings.getQuery());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking(resultSet.getInt("id"), resultSet.getInt("flight_id"), resultSet.getInt("passenger_id"));
                bookingList.add(booking);
            }
            LoggerHelper.info("All bookings fetched successfully");
        } catch (SQLException e) {
            LoggerHelper.error("Error while fetching bookings: " + e.getMessage());
        }
        return bookingList;
    }
}
