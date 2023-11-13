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

    public int getPrice() {
        return menu.getPrice() * quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public boolean isBeverage() {
        return menu.isSameType("beverage");
    }

    public int getDessertQuantity() {
        if (menu.isSameType("dessert")) {
            return this.quantity;
        }
        return 0;
    }

    public int getMainQuantity() {
        if (menu.isSameType("main")) {
            return this.quantity;
        }
        return 0;
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
