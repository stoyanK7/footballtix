package bg.stoyank.footballtix.registration.token.exception;

public class EmailConfirmedException extends  RuntimeException{
    public EmailConfirmedException(String message) {
        super(message);
    }
}
