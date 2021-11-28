package bg.stoyank.footballtix.order.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super("Could not find order: " + message);
    }
}
