package christmas.domain;

import java.util.Arrays;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000);


    private final String description;
    private final int condition;

    EventBadge(String description, int condition) {
        this.description = description;
        this.condition = condition;
    }

    public static String getEventBadge(Orders orders) {
        return Arrays.stream(EventBadge.values())
                .filter(badge -> Event.calculateDiscountPrice(orders) >= badge.getCondition())
                .findFirst()
                .map(EventBadge::getDescription)
                .orElse("없음");
    }

    private int getCondition() {
        return this.condition;
    }

    private String getDescription() {
        return this.description;
    }
}
