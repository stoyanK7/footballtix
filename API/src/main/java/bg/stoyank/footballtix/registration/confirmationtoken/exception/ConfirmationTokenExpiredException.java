package bg.stoyank.footballtix.registration.confirmationtoken.exception;

public class ConfirmationTokenExpiredException extends RuntimeException{
    public ConfirmationTokenExpiredException(String message) {
        super(message);
    }
}
