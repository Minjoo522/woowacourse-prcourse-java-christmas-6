package christmas.config;

public enum Config {
    MENU_CSV_PATH("src/main/java/christmas/resource/menu.csv");

    private final String value;

    Config(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
