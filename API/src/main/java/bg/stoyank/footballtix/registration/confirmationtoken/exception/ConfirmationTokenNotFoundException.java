package bg.stoyank.footballtix.registration.confirmationtoken.exception;

public class ConfirmationTokenNotFoundException extends RuntimeException{
    public ConfirmationTokenNotFoundException() {
        super();
    }

    public ConfirmationTokenNotFoundException(String message) {
        super(message);
    }

    public ConfirmationTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfirmationTokenNotFoundException(Throwable cause) {
        super(cause);
    }
}
