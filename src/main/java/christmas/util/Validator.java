package christmas.util;

public class Validator {
    private Validator() {
        // 인스턴스 생성 방지용
    }

    public static int validateDate(String input) {
        validateNotNull(input);
        int date = Parser.stringParseInt(input);
        // TODO: 하드코딩된 값 변경하기
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
        return date;
    }

    public static void validateNotNull(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
