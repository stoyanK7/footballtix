package bg.stoyank.footballtix.registration.confirmationtoken.exception;

public class ConfirmationTokenExpiredException extends RuntimeException{
    public ConfirmationTokenExpiredException() {
        super();
    }

    public ConfirmationTokenExpiredException(String message) {
        super(message);
    }

    public ConfirmationTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfirmationTokenExpiredException(Throwable cause) {
        super(cause);
    }
}
