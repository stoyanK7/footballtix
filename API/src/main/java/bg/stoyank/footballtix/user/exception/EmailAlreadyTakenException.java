package bg.stoyank.footballtix.user.exception;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super("User with such email already exists: " + email);
    }
}
