package christmas.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private Parser() {
        // 인스턴스 생성 방지용
    }

    public static int stringParseInt(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static List<String> splitByComma(String input) {
        return Arrays.asList(input.trim().split(","));
    }
}
