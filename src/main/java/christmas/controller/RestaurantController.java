package christmas.controller;

import static christmas.config.Config.MENU_CSV_PATH;

import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.EventBadge;
import christmas.service.Parser;
import christmas.service.CSVReader;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class RestaurantController {
    Menus menus;
    int date;
    Orders orders;

    public void run() {
        menus = getMenus();
        date = InputView.readDate();
        orders = getOrders();
        OutputView.printEventSubject(date, orders.getOrderMenus());
        OutputView.printOriginalPrice(orders.getTotalPrice());
        getEvent();
    }

    private Menus getMenus() {
        List<Menu> menus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        return new Menus(menus);
    }

    private Orders getOrders() {
        List<String> input = InputView.readOrder();
        List<Order> orders = Parser.parseOrder(input, menus);
        return new Orders(date, orders);
    }

    private void getEvent() {
        OutputView.printPresent(Event.getPresent(orders));
        OutputView.printBenefits(Event.getBenefitDescription(orders));
        OutputView.printDiscountPrice(Event.getDiscountPrice(orders));
        OutputView.printFinalPrice(Event.getFinalPrice(orders));
        OutputView.printBadge(EventBadge.getEventBadge(orders));
    }
}
