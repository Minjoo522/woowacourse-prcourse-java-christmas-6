package christmas.domain;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Event {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", Event::canGetChristmasDDayDiscount,
            orders -> 1_000 + orders.calculateDiscountByDate(100)),
    WEEKDAY("평일 할인", Event::canGetWeekdaysDiscount, orders -> orders.calculateDessertCount() * 2_023),
    WEEKEND("주말 할인", Event::canGetWeekendDiscount, orders -> orders.calculateMainCount() * 2_023),
    SPECIAL("특별 할인", Event::canGetSpecialDiscount, orders -> 1_000),
    PRESENT("증정 이벤트", Event::canGetPresent, orders -> 25_000);

    private final String description;
    private final Function<Orders, Boolean> condition;
    private final Function<Orders, Integer> calculator;

    private static final String DESCRIPTION_BASE = "%s: -%,d원";
    private static final String NOTHING = "없음";

    Event(String description, Function<Orders, Boolean> condition, Function<Orders, Integer> calculator) {
        this.description = description;
        this.condition = condition;
        this.calculator = calculator;
    }

    public static String getPresent(Orders orders) {
        if (orders.getTotalPrice() < 120_000) {
            return NOTHING;
        }
        return "샴페인 1개";
    }

    public static String getBenefitDescription(Orders orders) {
        if (joinBenefitDescription(orders).isEmpty()) {
            return NOTHING;
        }
        return joinBenefitDescription(orders);
    }
    public static String getDiscountPrice(Orders orders) {
        if (calculateDiscountPrice(orders) == 0) {
            return "0원";
        }
        return String.format("-%,d원", calculateDiscountPrice(orders));
    }

    public static String getFinalPrice(Orders orders) {
        if (canGetBenefit(orders)) {
            int discountPrice = Arrays.stream(Event.values())
                    .filter(event -> event != Event.PRESENT)
                    .mapToInt(event -> event.calculateDiscount(orders))
                    .sum();
            int discountedPrice = orders.getTotalPrice() - discountPrice;
            return String.format("%,d원", discountedPrice);
        }
        return "0원";
    }

    public static int calculateDiscountPrice(Orders orders) {
        if (canGetBenefit(orders)) {
            return Arrays.stream(Event.values())
                    .mapToInt(event -> event.calculateDiscount(orders))
                    .sum();
        }
        return 0;
    }

    private static boolean canGetBenefit(Orders orders) {
        return orders.getTotalPrice() >= 10_000;
    }

    private static String joinBenefitDescription(Orders orders) {
        if (canGetBenefit(orders)) {
            return Arrays.stream(Event.values())
                    .map(event -> event.getDescription(orders))
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining("\n"));
        }
        return NOTHING;
    }

    private static boolean canGetChristmasDDayDiscount(Orders orders) {
        return orders.isInRange(1, 25);
    }

    private static boolean canGetWeekdaysDiscount(Orders orders) {
        if (orders.isWeekday() && orders.calculateDessertCount() > 0) {
            return true;
        }
        return false;
    }

    private static boolean canGetWeekendDiscount(Orders orders) {
        if (orders.isWeekend() && orders.calculateMainCount() > 0) {
            return true;
        }
        return false;
    }

    private static boolean canGetSpecialDiscount(Orders orders) {
        return orders.isSameDate(List.of(3, 10, 17, 24, 25, 31));
    }

    private static boolean canGetPresent(Orders orders) {
        return orders.getTotalPrice() >= 120_000 && canGetBenefit(orders);
    }

    private String getDescription(Orders orders) {
        if (condition.apply(orders)) {
            return String.format(DESCRIPTION_BASE, description, calculator.apply(orders));
        }
        return null;
    }

    private int calculateDiscount(Orders orders) {
        if (condition.apply(orders)) {
            return calculator.apply(orders);
        }
        return 0;
    }
}
