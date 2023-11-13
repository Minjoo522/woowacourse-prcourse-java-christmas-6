package christmas.domain;

import christmas.exception.EventException;
import java.util.List;

public class Menus {
    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public Menu findMenuByName(String name) throws EventException {
        return menus.stream()
                .filter(menu -> menu.isSameName(name))
                .findFirst()
                .orElseThrow(EventException::new);
    }

    public boolean hasSameMenu(String name) {
        return menus.stream()
                .anyMatch(manu -> manu.isSameName(name));
    }

}
