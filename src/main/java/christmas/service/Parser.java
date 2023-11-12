package christmas.service;

import christmas.domain.Menus;
import christmas.domain.Order;
import christmas.util.Validator;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Parser {
    private Parser() {
        // 인스턴스 생성 방지용
    }

    public static List<Order> parseOrder(List<String> input, Menus menus) {
        List<Order> result = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (String item: input) {
            String[] parts = splitByDash(item);
            Validator.validateSize(parts);
            names.add(parts[0]);
            result.add(new Order(menus.findMenuByName(parts[0]), stringParseInt(parts[1])));
        }
        Validator.validateNotDuplicate(names);
        return result;
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

    public static String[] splitByDash(String input) {
        return input.trim().split("-");
    }
}
