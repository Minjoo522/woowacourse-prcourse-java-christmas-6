package christmas.domain;

public class Order {
    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        validate(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public String getNameAndQuantity() {
        return String.format("%s %d개", menu.getName(), quantity);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public boolean isBeverage() {
        return menu.isSameType("beverage");
    }

    private void validate(int quantity) {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }
    }
}
