package christmas.domain;

import static christmas.config.Config.MENU_CSV_PATH;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.CSVReader;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class EventBadgeTest {
    Menus menus;
    List<Order> orders;

    @BeforeEach
    void init() {
        List<Menu> rawMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        menus = new Menus(rawMenus);
        orders = new ArrayList<>();
    }

    @DisplayName("[Success] 총 혜택 받은 금액이 5,000원 미만이면 없음을 리턴한다.")
    @Test
    void tryGetBadgeLowerPrice() {
        orders.add(new Order(menus.findMenuByName("초코케이크"), 1)); // 총 혜택 금액 : 3,023원

        assertThat(EventBadge.getEventBadge(new Orders(31, orders)))
                .isEqualTo("없음");
    }

    @DisplayName("[Success] 총 혜택 받은 금액이 5,000원 이상 10,000원 미만이면 별을 리턴한다.")
    @Test
    void getStar() {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 2)); // 총 혜택 금액 : 5,046원

        assertThat(EventBadge.getEventBadge(new Orders(31, orders)))
                .isEqualTo("별");
    }

    @DisplayName("[Success] 총 혜택 받은 금액이 10,000원 이상 20,000원 미만이면 트리를 리턴한다.")
    @Test
    void getTree() {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 5)); // 총 혜택 금액 : 11,115원

        assertThat(EventBadge.getEventBadge(new Orders(31, orders)))
                .isEqualTo("트리");
    }

    @DisplayName("[Success] 총 혜택 받은 금액이 20,000원 이상이면 산타를 리턴한다.")
    @Test
    void getSante() {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), 3)); // 총 혜택 금액 : 26,000원

        assertThat(EventBadge.getEventBadge(new Orders(31, orders)))
                .isEqualTo("산타");
    }
}
