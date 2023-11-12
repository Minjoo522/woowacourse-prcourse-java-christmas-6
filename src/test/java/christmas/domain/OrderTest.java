package christmas.domain;

import static christmas.config.Config.MENU_CSV_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.service.CSVReader;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    Menus menus;

    @BeforeEach
    void init() {
        List<Menu> rawMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        menus = new Menus(rawMenus);
    }

    @DisplayName("[Exception] 주문 개수가 1개 미만이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void createOrderByLessThanOne(int input) {
        Menu menu = menus.findMenuByName("양송이수프");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Order(menu, input));
    }

    @DisplayName("[Success] 주문한 메뉴가 음료이면 true를, 음료가 아니면 false를 리턴한다.")
    @ParameterizedTest
    @CsvSource(value = {"제로콜라:true", "양송이수프:false"}, delimiter = ':')
    void checkIsBeverage(String input, boolean expected) {
        Menu menu = menus.findMenuByName(input);
        Order order = new Order(menu, 1);
        assertThat(order.isBeverage())
                .isEqualTo(expected);
    }
}
