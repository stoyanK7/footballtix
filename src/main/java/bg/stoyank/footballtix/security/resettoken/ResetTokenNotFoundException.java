package bg.stoyank.footballtix.security.resettoken;

public class ResetTokenNotFoundException extends RuntimeException {
    public ResetTokenNotFoundException(String message) {
        super(message);
    }
}
