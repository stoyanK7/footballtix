package bg.stoyank.footballtix.footballmatch.exception;

public class FootballMatchNotFoundException extends RuntimeException{
    public FootballMatchNotFoundException() {
        super();
    }

    public FootballMatchNotFoundException(String message) {
        super(message);
    }

    public FootballMatchNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FootballMatchNotFoundException(Throwable cause) {
        super(cause);
    }
}
