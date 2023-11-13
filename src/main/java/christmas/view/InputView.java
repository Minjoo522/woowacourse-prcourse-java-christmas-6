package christmas.view;

import static christmas.view.Message.ASK_EXPECTED_DATE;
import static christmas.view.Message.ASK_ORDER_MENU;
import static christmas.view.Message.GREETING;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validator;
import christmas.service.Parser;
import java.util.List;

public class InputView {
    private InputView() {
        // 인스턴스 생성 방지용
    }

    public static int readDate() {
        System.out.println(GREETING.getMessage());
        System.out.println(ASK_EXPECTED_DATE.getMessage());
        String input = Console.readLine();
        return Validator.validateDate(input);
    }

    public static List<String> readOrder() {
        System.out.println(ASK_ORDER_MENU.getMessage());
        String input = Console.readLine();
        Validator.validateNotNull(input);
        return Parser.splitByComma(input);
    }
}