package exception;

public class NoSuchSubscriptionException extends RuntimeException {

    public NoSuchSubscriptionException(String message, String s) {
        super(String.format(message, s));
    }
}
