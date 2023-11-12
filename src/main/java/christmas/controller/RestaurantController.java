package christmas.controller;

import static christmas.config.Config.MENU_CSV_PATH;

import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.service.Parser;
import christmas.service.CSVReader;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class RestaurantController {
    Menus menus;
    int date;
    Orders orders;

    public void run() {
        menus = getMenus();
        InputView.printGreeting();
        date = InputView.readDate();
        orders = getOrders();
        OutputView.printEventSubject(date, orders.getOrderMenus());
        OutputView.printOriginalPrice(orders.getTotalPrice());
    }

    private Menus getMenus() {
        List<Menu> menus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        return new Menus(menus);
    }

    private Orders getOrders() {
        List<String> input = InputView.readOrder();
        Validator.validateNotDuplicate(input); // 여기서하면 안됨
        List<Order> orders = Parser.parseOrder(input, menus);
        return new Orders(date, orders);
    }
}
