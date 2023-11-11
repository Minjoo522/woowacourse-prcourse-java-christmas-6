package christmas.domain;

import christmas.util.Parser;

public class Menu {
    private final String type;
    private final String name;
    private final int price;

    public Menu(String type, String name, String price) {
        this.type = type;
        this.name = name;
        this.price = Parser.stringParseInt(price);
    }
}
