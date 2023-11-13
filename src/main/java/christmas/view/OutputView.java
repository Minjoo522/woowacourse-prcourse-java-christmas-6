package christmas.view;

import static christmas.view.Message.NOTICE_BADGE_SUBJECT;
import static christmas.view.Message.NOTICE_ORDER_MENU;
import static christmas.view.Message.NOTICE_EVENT_SUBJECT;
import static christmas.view.Message.NOTICE_ORIGINAL_PRICE;
import static christmas.view.Message.NOTICE_PRESENT_SUBJECT;
import static christmas.view.Message.NOTICE_BENEFITS_SUBJECT;
import static christmas.view.Message.NOTICE_FINAL_PRICE_SUBJECT;
import static christmas.view.Message.NOTICE_DISCOUNT_PRICE_SUBJECT;

import christmas.exception.ExceptionMessage;
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
        printEmptyLine();
    }

    public static void printOriginalPrice(int price) {
        printDynamicMessage(NOTICE_ORIGINAL_PRICE, price);
        printEmptyLine();
    }

    public static void printPresent(String result) {
        printMessage(NOTICE_PRESENT_SUBJECT);
        System.out.println(result);
        printEmptyLine();
    }

    public static void printBenefits(String result) {
        printMessage(NOTICE_BENEFITS_SUBJECT);
        System.out.println(result);
        printEmptyLine();
    }

    public static void printDiscountPrice(String result) {
        printMessage(NOTICE_DISCOUNT_PRICE_SUBJECT);
        System.out.println(result);
        printEmptyLine();
    }

    public static void printFinalPrice(String result) {
        printMessage(NOTICE_FINAL_PRICE_SUBJECT);
        System.out.println(result);
        printEmptyLine();
    }

    public static void printBadge(String result) {
        printMessage(NOTICE_BADGE_SUBJECT);
        System.out.println(result);
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

    public static void printErrorMessage(ExceptionMessage message) {
        System.out.println(message.getMessage());
    }
}