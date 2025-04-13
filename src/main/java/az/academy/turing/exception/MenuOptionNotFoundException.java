package az.academy.turing.exception;

public class MenuOptionNotFoundException extends RuntimeException {
    public MenuOptionNotFoundException(String message) {
        super(message);
    }
}
