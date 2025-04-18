package az.academy.turing.exception;

public class InvalidSelectionException extends AppException {
    public InvalidSelectionException(int exe) {
        super("Invalid selection: " + exe + "'");
    }
}
