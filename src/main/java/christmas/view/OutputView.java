package christmas.view;

import static christmas.view.constant.Message.NOTICE_EVENT_SUBJECT;
import static christmas.view.constant.Message.NOTICE_ORDER_MENU;

import christmas.view.constant.Message;
import java.util.List;

public class OutputView {
    private OutputView() {
        // 인스턴스 생성 방지용
    }

    public static void printEventSubject(int date, List<String> messsages) {
        printDynamicMessage(NOTICE_EVENT_SUBJECT, date);
        printEmptyLine();
        printMessage(NOTICE_ORDER_MENU);
        printIterableMessage(messsages);
    }
    private static void printMessage(Message message) {
        System.out.println(message.getMessage());
    }

    private static void printDynamicMessage(Message message, Object... args) {
        System.out.printf(message.getMessage(), args);
        printEmptyLine();
    }

    private static void printIterableMessage(Iterable<?> iterable) {
        iterable.forEach(System.out::println);
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}