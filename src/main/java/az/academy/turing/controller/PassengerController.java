package az.academy.turing.controller;

import az.academy.turing.dto.PassengerRequestDto;
import az.academy.turing.dto.PassengerResponseDto;
import az.academy.turing.service.PassengerService;

import java.util.List;

public class PassengerController implements PassengerService {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @Override
    public void savePassenger(PassengerRequestDto passengerRequestDto) {
        passengerService.savePassenger(passengerRequestDto);

    }

    @Override
    public void deletePassenger(int id) {
        passengerService.deletePassenger(id);
    }

    @Override
    public PassengerResponseDto findById(int id) {
        return passengerService.findById(id);
    }

    @Override
    public List<PassengerResponseDto> findAll() {
        return passengerService.findAll();
    }

    @Override
    public void updatePassengerInfo(int id, PassengerRequestDto passengerRequestDto) {
        passengerService.updatePassengerInfo(id, passengerRequestDto);
    }
}
