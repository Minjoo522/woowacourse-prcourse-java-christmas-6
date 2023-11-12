package christmas.domain;

import java.util.List;

public class Orders {
    private final int date;
    private final List<Order> orders;

    public Orders(int date, List<Order> orders) {
        validate(orders);
        this.date = date;
        this.orders = orders;
    }

    private void validate(List<Order> orders) {
        validateTotalQuantity(orders);
        validateNotOnlyBeverage(orders);
    }

    private void validateTotalQuantity(List<Order> orders) {
        int total = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
        if (total > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotOnlyBeverage(List<Order> orders) {
        boolean hasOnlyBeverage = orders.stream()
                .anyMatch(Order::isBeverage);
        if (hasOnlyBeverage) {
            throw new IllegalArgumentException();
        }
    }
}
