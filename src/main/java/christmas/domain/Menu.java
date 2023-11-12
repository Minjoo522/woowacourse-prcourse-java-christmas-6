package christmas.domain;

import christmas.service.Parser;

public class Menu {
    private final String type;
    private final String name;
    private final int price;

    public Menu(String type, String name, String price) {
        this.type = type;
        this.name = name;
        this.price = Parser.stringParseInt(price);
    }

    public String getName() {
        return this.name;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public boolean isSameType(String type) {
        return this.type.equals(type);
    }
}
