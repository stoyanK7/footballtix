package bg.stoyank.footballtix.registration.confirmationtoken.exception;

public class EmailConfirmedException extends  RuntimeException{
    public EmailConfirmedException(String message) {
        super(message);
    }
}
