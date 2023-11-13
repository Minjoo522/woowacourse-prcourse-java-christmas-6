package christmas.exception;

public enum ExceptionMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    SYSTEM_EXCEPTION("메뉴판을 읽어오는데 문제가 발생했습니다.");

    public static final String BASE_MESSAGE = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = BASE_MESSAGE + String.format(message);
    }

    public String getMessage() {
        return message;
    }
}
