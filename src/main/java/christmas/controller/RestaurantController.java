package christmas.controller;

import static christmas.config.Config.MENU_CSV_PATH;
import static christmas.exception.ExceptionMessage.INVALID_DATE;
import static christmas.exception.ExceptionMessage.INVALID_ORDER;
import static christmas.exception.ExceptionMessage.SYSTEM_EXCEPTION;

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
import christmas.exception.EventException;
import java.util.List;

public class RestaurantController {
    Menus menus;
    int date;
    Orders orders;

    public void run() {
        getMenus();
        getDate();
        getOrders();
        getEvent();
    }

    private void getMenus() {
        while (true) {
            try {
                List<Menu> newMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
                menus = new Menus(newMenus);
                return;
            } catch (EventException e) {
                OutputView.printErrorMessage(SYSTEM_EXCEPTION);
            }
        }
    }

    private void getDate() {
        while (true) {
            try {
                date = InputView.readDate();
                return;
            } catch (EventException e) {
                OutputView.printErrorMessage(INVALID_DATE);
            }
        }
    }

    private void getOrders() {
        while (true) {
            try {
                List<String> input = InputView.readOrder();
                List<Order> newOrders = Parser.parseOrder(input, menus);
                orders = new Orders(date, newOrders);
                OutputView.printEventSubject(date, orders.getOrderMenus());
                OutputView.printOriginalPrice(orders.getTotalPrice());
                return;
            } catch (EventException e) {
                OutputView.printErrorMessage(INVALID_ORDER);
            }
        }
    }

    private void getEvent() {
        OutputView.printPresent(Event.getPresent(orders));
        OutputView.printBenefits(Event.getBenefitDescription(orders));
        OutputView.printDiscountPrice(Event.getDiscountPrice(orders));
        OutputView.printFinalPrice(Event.getFinalPrice(orders));
        OutputView.printBadge(EventBadge.getEventBadge(orders));
    }
}
