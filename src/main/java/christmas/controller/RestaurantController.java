package christmas.controller;

import static christmas.config.Config.MENU_CSV_PATH;

import christmas.domain.Order;
import christmas.service.CSVReader;
import christmas.domain.Menus;
import christmas.domain.Menu;
import christmas.view.InputView;
import java.util.List;

public class RestaurantController {
    public void run() {
        Menus menus = getMenus();
        InputView.printGreeting();
        int date = InputView.readDate();
        Order order = new Order(date);
    }

    private Menus getMenus() {
        List<Menu> menus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        return new Menus(menus);
    }
}
