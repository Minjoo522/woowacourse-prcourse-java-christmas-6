package christmas.domain;

import christmas.util.DateUtil;
import christmas.exception.EventException;
import java.util.List;
import java.util.stream.Collectors;
import java.time.DayOfWeek;

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

    public boolean isInRange(int start, int end) {
        return date >= start && date <= end;
    }

    public int calculateDiscountByDate(int price) {
        return price * (date - 1);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = DateUtil.getDayOfWeek(2023, 12, date);
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        DayOfWeek dayOfWeek = DateUtil.getDayOfWeek(2023, 12, date);
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public boolean isSameDate(List<Integer> dates) {
        return dates.stream()
                .anyMatch(date -> date == this.date);
    }

    public int calculateDessertCount() {
        return orders.stream()
                .mapToInt(Order::getDessertQuantity)
                .sum();
    }

    public int calculateMainCount() {
        return orders.stream()
                .mapToInt(Order::getMainQuantity)
                .sum();
    }

    private void validate(List<Order> orders) {
        validateTotalQuantity(orders);
        validateNotOnlyBeverage(orders);
    }

    private void validateTotalQuantity(List<Order> orders) throws EventException {
        int total = orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
        if (total > 20) {
            throw new EventException();
        }
    }

    private void validateNotOnlyBeverage(List<Order> orders) throws EventException {
        boolean hasOnlyBeverage = orders.stream()
                .allMatch(Order::isBeverage);
        if (hasOnlyBeverage) {
            throw new EventException();
        }
    }
}
