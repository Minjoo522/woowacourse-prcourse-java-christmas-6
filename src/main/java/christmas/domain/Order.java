package christmas.domain;

public class Order {
    private final int date;

    public Order(int date) {
        validateDate(date);
        this.date = date;
    }

    private void validateDate(int date) {
        // TODO: 하드코딩된 값 변경하기
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }
}
