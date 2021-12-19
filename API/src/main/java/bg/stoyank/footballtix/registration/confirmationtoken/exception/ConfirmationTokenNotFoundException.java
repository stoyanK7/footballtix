package bg.stoyank.footballtix.registration.confirmationtoken.exception;

public class ConfirmationTokenNotFoundException extends RuntimeException{
    public ConfirmationTokenNotFoundException(String message) {
        super(message);
    }
}
