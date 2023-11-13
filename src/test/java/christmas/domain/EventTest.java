package christmas.domain;

import static christmas.config.Config.MENU_CSV_PATH;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.CSVReader;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EventTest {
    Menus menus;
    List<Order> orders;

    @BeforeEach
    void init() {
        List<Menu> rawMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        menus = new Menus(rawMenus);
        orders = new ArrayList<>();
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 미만이면 아무런 혜택도 받을 수 없다.")
    @Test
    void tryGetDiscountLowerPrice() {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 1)); // 5,000원

        assertThat(Event.getBenefitDescription(new Orders(25, orders)))
                .isEqualTo("없음");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 120,000원 이상이면 샴페인 1개를 증정 받는다.")
    @Test
    void getChampagne() {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), 2));
        orders.add(new Order(menus.findMenuByName("아이스크림"), 2));

        assertThat(Event.getPresent(new Orders(25, orders)))
                .isEqualTo("샴페인 1개");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이지만 120,000원 미만이면 없음을 리턴한다.")
    @Test
    void tryGetChampagneLowerPrice() {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 3)); // 15,000원

        assertThat(Event.getPresent(new Orders(25, orders)))
                .isEqualTo("없음");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이고 일자가 25일 이내이면 "
            + "1,000 + 100 * (날짜 - 1) 금액의 크리스마스 디데이 할인을 받는다.")
    @ParameterizedTest
    @CsvSource(value = {"1:1000", "15:2400", "25:3400"}, delimiter = ':')
    void getChristmasDDayDiscount(int date, int price) {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 2));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .contains(String.format("크리스마스 디데이 할인: -%,d원", price));
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이지만 방문 일자가 25일 이후면 크리스마스 디데이 할인을 받지 못한다.")
    @Test
    void tryGetChristmasDDayDiscountByNotQualifiedDate() {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 2));

        assertThat(Event.getBenefitDescription(new Orders(26, orders)))
                .doesNotContain("크리스마스 디데이 할인");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이고 평일이면 주문한 디저트 1개당 2_023원씩 할인을 받는다.")
    @ParameterizedTest
    @CsvSource(value = {"3:1:2023", "17:2:4046", "24:3:6069"}, delimiter = ':')
    void getWeekdaysDiscount(int date, int quantity, int price) {
        orders.add(new Order(menus.findMenuByName("초코케이크"), quantity));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .contains(String.format("평일 할인: -%,d원", price));
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이고 평일이어도 디저트를 주문하지 않았으면 할인을 받지 못한다.")
    @ParameterizedTest
    @CsvSource(value = {"3:1", "17:2", "24:3"}, delimiter = ':')
    void tryGetWeekdaysDiscountNotContainDessert(int date, int quantity) {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), quantity));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .doesNotContain("평일 할인");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이고 주말이면 메인 한 개당 2_203원씩 할인 받는다.")
    @ParameterizedTest
    @CsvSource(value = {"1:1:2023", "8:2:4046", "23:3:6069"}, delimiter = ':')
    void getWeekendsDiscount(int date, int quantity, int price) {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), quantity));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .contains(String.format("주말 할인: -%,d원", price));
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이고 주말이어도 메인을 주문하지 않았으면 할인을 받지 못한다.")
    @ParameterizedTest
    @CsvSource(value = {"1:1", "8:2", "23:3"}, delimiter = ':')
    void tryGetWeekendsDiscountNotContainDessert(int date, int quantity) {
        orders.add(new Order(menus.findMenuByName("초코케이크"), quantity));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .doesNotContain("주말 할인");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이고 특별 할인 기간에 주문하면 1,000원을 할인 받는다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void getSpecialDiscount(int date) {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 2));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .contains("특별 할인: -1,000원");
    }

    @DisplayName("[Success] 할인 전 주문 금액이 10,000원 이상이지만 특별 할인 기간에 주문하지 않으면 할인 받지 못한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 7, 14, 26, 28})
    void tryGetSpecialDiscountByOutBoundDate(int date) {
        orders.add(new Order(menus.findMenuByName("아이스크림"), 2));

        assertThat(Event.getBenefitDescription(new Orders(date, orders)))
                .doesNotContain("특별 할인");
    }

    @DisplayName("[Success] 할인을 중복하여 받을 수 있다.")
    @Test
    void getDoubleDiscount() {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), 1));
        orders.add(new Order(menus.findMenuByName("바비큐립"), 1));
        orders.add(new Order(menus.findMenuByName("초코케이크"), 2));
        orders.add(new Order(menus.findMenuByName("제로콜라"), 1));

        assertThat(Event.getBenefitDescription(new Orders(3, orders)))
                .isEqualTo(String.join(System.lineSeparator(),
                        "크리스마스 디데이 할인: -1,200원",
                        "평일 할인: -4,046원",
                        "특별 할인: -1,000원",
                        "증정 이벤트: -25,000원"));
    }

    @DisplayName("[Success] 할인 받은 총 금액을 합산하여 리턴한다.")
    @Test
    void getTotalDiscountPrice() {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), 1));
        orders.add(new Order(menus.findMenuByName("바비큐립"), 1));
        orders.add(new Order(menus.findMenuByName("초코케이크"), 2));
        orders.add(new Order(menus.findMenuByName("제로콜라"), 1));

        assertThat(Event.getDiscountPrice(new Orders(3, orders)))
                .isEqualTo("-31,246원");
    }

    @DisplayName("[Success] 할인 후 예상 결제 금액을 리턴한다. 단, 샴페인 금액은 제외한다.")
    @Test
    void getFinalPrice() {
        orders.add(new Order(menus.findMenuByName("티본스테이크"), 1));
        orders.add(new Order(menus.findMenuByName("바비큐립"), 1));
        orders.add(new Order(menus.findMenuByName("초코케이크"), 2));
        orders.add(new Order(menus.findMenuByName("제로콜라"), 1));

        assertThat(Event.getFinalPrice(new Orders(3, orders)))
                .isEqualTo("135,754원");
    }
}
