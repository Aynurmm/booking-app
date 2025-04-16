package az.academy.turing.service.serviceImpl;

import az.academy.turing.dao.PassengerDao;
import az.academy.turing.dto.PassengerRequestDto;
import az.academy.turing.dto.PassengerResponseDto;
import az.academy.turing.helper.LoggerHelper;
import az.academy.turing.model.Passenger;
import az.academy.turing.service.PassengerService;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerServiceImpl implements PassengerService {
    private final PassengerDao passengerDao;

    public PassengerServiceImpl(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Override
    public void savePassenger(PassengerRequestDto passengerRequestDto) {
        Passenger passenger=new Passenger();
        passenger.setId(passengerRequestDto.getId());
        passenger.setFirst_name(passengerRequestDto.getFirst_name());
        passenger.setLast_name(passengerRequestDto.getLast_name());
        passenger.setLogin(passengerRequestDto.getLogin());
        passenger.setPassword(passengerRequestDto.getPassword());
        passengerDao.savePassenger(passenger);
    }

    @Override
    public void deletePassenger(int id) {
        passengerDao.deletePassenger(id);

    }

    @Override
    public PassengerResponseDto findById(int id) {
       Passenger passenger=passengerDao.findById(id);
      if (passenger==null){
          return null;
      }
      return new PassengerResponseDto(passenger.getFirst_name(),passenger.getLast_name());
    }

    @Override
    public List<PassengerResponseDto> findAll() {
        List<Passenger>passengerList=passengerDao.findAll();
        return passengerList.stream().
                map(p->new PassengerResponseDto(p.getFirst_name(),p.getLast_name()))
                .collect(Collectors.toList());
    }

    @Override
    public void updatePassengerInfo(int id, PassengerRequestDto passengerRequestDto) {
        Passenger passenger=new Passenger();
        passenger.setId(passengerRequestDto.getId());
        passenger.setFirst_name(passengerRequestDto.getFirst_name());
        passenger.setLast_name(passengerRequestDto.getLast_name());
        passenger.setLogin(passengerRequestDto.getLogin());
        passenger.setPassword(passengerRequestDto.getPassword());
        passengerDao.updatePassengerInfo(id,passenger);
    }

}
