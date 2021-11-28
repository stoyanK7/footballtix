package bg.stoyank.footballtix.order.exception;

public class OrderAlreadyExistsException extends RuntimeException{
    public OrderAlreadyExistsException(String message) {
        super("Order already exists: " + message);
    }
}
