package christmas.exception;

public class EventException extends IllegalArgumentException {
    public EventException() {
        super();
    }
    public EventException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }

    public EventException(ExceptionMessage exceptionMessage, Throwable cause) {
        super(exceptionMessage.getMessage(), cause);
    }

}
