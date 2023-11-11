package christmas.util;

import java.util.List;

public class Validator {
    private Validator() {
        // 인스턴스 생성 방지용
    }

    public static void validateNotNull(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
