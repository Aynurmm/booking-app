package az.academy.turing.exception;

public class PassengerNotFoundException extends AppException {
    public PassengerNotFoundException(String login) {

        super("Passenger not Found:"+login);
    }
}
