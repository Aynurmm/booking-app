package az.academy.turing.dao.daoImpl;

import az.academy.turing.config.ConfigHelper;
import az.academy.turing.dao.PassengerDao;
import az.academy.turing.enums.PassengerQuery;
import az.academy.turing.helper.LoggerHelper;
import az.academy.turing.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDaoImpl implements PassengerDao {
    @Override
    public void savePassenger(Passenger passenger) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PassengerQuery.insert_passenger.getQuery());
            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setString(2, passenger.getFirst_name());
            preparedStatement.setString(3, passenger.getLast_name());
            preparedStatement.setString(4, passenger.getLogin());
            preparedStatement.setString(5, passenger.getPassword());
            preparedStatement.executeUpdate();
            LoggerHelper.info("passenger saved successfully");
        } catch (SQLException e) {
            LoggerHelper.error("error while saving passenger with id: " + passenger.getId());
        }
    }

    @Override
    public void deletePassenger(int id) {
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PassengerQuery.delete_passengerById.getQuery());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            LoggerHelper.info("passenger deleted successfully with id: " + id);
        } catch (SQLException e) {
            LoggerHelper.error("unable to delete passenger with id: " + id);
        }
    }

    @Override
    public Passenger findById(int id) {
        Passenger passenger = new Passenger();
        try (Connection connection = ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PassengerQuery.getPassenger_ById.getQuery());
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                passenger.setFirst_name(res.getString("first_name"));
                passenger.setLast_name(res.getString("last_name"));
                passenger.setLogin(res.getString("login"));
                passenger.setPassword(res.getString("password"));
            }
            LoggerHelper.info("passenger fetched successfully by id");
        } catch (SQLException e) {
            LoggerHelper.error("error while fetching passenger by id , with id:" + passenger.getId());
        }
        ;
        return passenger;
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger>passengerList=new ArrayList<>();
     try(Connection connection=ConfigHelper.getConnection()) {
         PreparedStatement preparedStatement=connection.prepareStatement(PassengerQuery.get_allPassengers.getQuery());
         ResultSet resultSet=preparedStatement.executeQuery();
         while (resultSet.next()){
             Passenger passenger=new Passenger();
             passenger.setId(resultSet.getInt("id"));
             passenger.setFirst_name(resultSet.getString("first_name"));
             passenger.setLast_name(resultSet.getString("last_name"));
             passenger.setLogin(resultSet.getString("login"));
             passenger.setPassword(resultSet.getString("password"));
             passengerList.add(passenger);
             LoggerHelper.info("all passengers fetched successfully");
         }
     } catch (SQLException e) {
         LoggerHelper.error("error while fetching passengers");
     }
     return passengerList;
    }

    @Override
    public void updatePassengerInfo(int id, Passenger passenger) {
        try(Connection connection=ConfigHelper.getConnection()) {
            PreparedStatement preparedStatement=connection.prepareStatement(PassengerQuery.update_passengerById.getQuery());
            preparedStatement.setString(1,passenger.getFirst_name());
            preparedStatement.setString(2,passenger.getLast_name());
            preparedStatement.setString(3,passenger.getLogin());
            preparedStatement.setString(4,passenger.getPassword());
            preparedStatement.setInt(5,passenger.getId());
            int rows=preparedStatement.executeUpdate();
            if (rows>0){
                LoggerHelper.info("Passenger with id: "+id+" is updated");

            }
        } catch (SQLException e) {
            LoggerHelper.error("there is no passenger with id: "+id);
        }

    }
}
