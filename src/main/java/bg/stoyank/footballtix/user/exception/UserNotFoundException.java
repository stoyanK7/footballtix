package bg.stoyank.footballtix.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String user) {
        super("User not found: " + user);
    }
}
