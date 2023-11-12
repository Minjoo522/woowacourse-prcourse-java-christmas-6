package christmas.domain;

import java.util.List;

public class Menus {
    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public Menu findMenuByName(String name) {
        return menus.stream()
                .filter(menu -> menu.isSameName(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean hasSameMenu(String name) {
        return menus.stream()
                .anyMatch(manu -> manu.isSameName(name));
    }

}
