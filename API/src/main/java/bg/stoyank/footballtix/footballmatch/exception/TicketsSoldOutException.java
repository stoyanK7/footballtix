package bg.stoyank.footballtix.footballmatch.exception;

public class TicketsSoldOutException extends RuntimeException{
    public TicketsSoldOutException(Long message) {
        super("Tickets are sold out for match: " + message);
    }
}
