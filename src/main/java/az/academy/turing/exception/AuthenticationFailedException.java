package az.academy.turing.exception;

public class AuthenticationFailedException extends AppException {
    public AuthenticationFailedException() {
        super("Entrance failed.Your information is wrong!");
    }
}
