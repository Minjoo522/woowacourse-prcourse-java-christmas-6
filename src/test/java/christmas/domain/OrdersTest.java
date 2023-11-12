package christmas.domain;

import static christmas.config.Config.MENU_CSV_PATH;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.service.CSVReader;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrdersTest {
    Menus menus;
    int date = 23;
    List<Order> orders;

    @BeforeEach
    void init() {
        List<Menu> rawMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        menus = new Menus(rawMenus);
        orders = new ArrayList<>();
    }

    @DisplayName("[Exception] 총 주문 개수가 20개를 초과하면 예외가 발생한다.")
    @Test
    void orderByGreaterThan20() {
        orders.add(new Order(menus.findMenuByName("양송이수프"), 21));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Orders(date, orders));
    }

    @DisplayName("[Exception] 음료만 주문하면 예외가 발생한다.")
    @Test
    void orderOnlyBeverage() {
        orders.add(new Order(menus.findMenuByName("제로콜라"), 1));
        orders.add(new Order(menus.findMenuByName("레드와인"), 1));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Orders(date, orders));
    }
}
