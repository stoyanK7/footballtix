package bg.stoyank.footballtix.footballmatch.exception;

public class FootballMatchNotFoundException extends RuntimeException{
    public FootballMatchNotFoundException(String message) {
        super(message);
    }
}
