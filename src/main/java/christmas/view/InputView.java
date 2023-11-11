package christmas.view;

import static christmas.view.constant.Message.ASK_EXPECTED_DATE;
import static christmas.view.constant.Message.ASK_ORDER_MENU;
import static christmas.view.constant.Message.GREETING;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validator;
import christmas.util.Parser;
import java.util.Map;

public class InputView {
    private InputView() {
        // 인스턴스 생성 방지용
    }

    public static void printGreeting() {
        System.out.println(GREETING.getMessage());
    }

    public static int readDate() {
        System.out.println(ASK_EXPECTED_DATE.getMessage());
        String input = Console.readLine();
        return Validator.validateDate(input);
    }
}