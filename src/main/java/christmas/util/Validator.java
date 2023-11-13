package christmas.util;

import christmas.exception.EventException;
import christmas.service.Parser;
import java.util.List;

public class Validator {
    private Validator() {
        // 인스턴스 생성 방지용
    }

    public static int validateDate(String input) throws EventException {
        validateNotNull(input);
        int date = Parser.stringParseInt(input);
        // TODO: 하드코딩된 값 변경하기
        if (date < 1 || date > 31) {
            throw new EventException();
        }
        return date;
    }

    public static void validateNotNull(String input) throws EventException {
        if (input == null || input.trim().isEmpty()) {
            throw new EventException();
        }
    }

    public static void validateNotDuplicate(List<String> names) throws EventException {
        if (isDuplicated(names)) {
            throw new EventException();
        }
    }

    public static void validateSize(String[] parts) throws EventException {
        if (parts.length != 2) {
            throw new EventException();
        }
    }

    private static boolean isDuplicated(List<String> names) {
        long uniqueName = names.stream()
                .distinct()
                .count();
        return uniqueName < names.size();
    }
}
