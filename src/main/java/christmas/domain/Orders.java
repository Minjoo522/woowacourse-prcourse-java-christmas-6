package christmas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Orders {
    private final int date;
    private final List<Order> orders;

    public Orders(int date, List<Order> orders) {
        validate(orders);
        this.date = date;
        this.orders = orders;
    }

    public List<String> getOrderMenus() {
        return orders.stream()
                .map(Order::getNameAndQuantity)
                .collect(Collectors.toList());
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(Order::getPrice)
                .sum();
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
                .allMatch(Order::isBeverage);
        if (hasOnlyBeverage) {
            throw new IllegalArgumentException();
        }
    }
}
