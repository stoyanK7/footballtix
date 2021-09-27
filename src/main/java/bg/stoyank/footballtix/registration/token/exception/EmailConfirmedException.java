package bg.stoyank.footballtix.registration.token.exception;

public class EmailConfirmedException extends  RuntimeException{
    public EmailConfirmedException() {
        super();
    }

    public EmailConfirmedException(String message) {
        super(message);
    }

    public EmailConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailConfirmedException(Throwable cause) {
        super(cause);
    }
}
