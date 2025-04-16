package az.academy.turing.service;

import az.academy.turing.dto.PassengerRequestDto;
import az.academy.turing.dto.PassengerResponseDto;

import java.util.List;

public interface PassengerService {
    void savePassenger(PassengerRequestDto passengerRequestDto);
    void deletePassenger(int id);
    PassengerResponseDto findById(int id);
    List<PassengerResponseDto> findAll();
    void updatePassengerInfo(int id,PassengerRequestDto passengerRequestDto);
}
