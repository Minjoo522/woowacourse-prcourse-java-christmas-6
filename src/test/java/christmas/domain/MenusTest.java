package christmas.domain;

import static christmas.config.Config.MENU_CSV_PATH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import christmas.service.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenusTest {
    Menus menus;

    @BeforeEach
    void init() {
        List<Menu> rawMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        menus = new Menus(rawMenus);
    }

    @DisplayName("[Success] 존재하는 메뉴를 찾으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "제로콜라", "초코케이크", "크리스마스파스타"})
    void checkIsExistMenu(String input) {
        assertThat(menus.hasSameMenu(input))
                .isTrue();
    }

    @DisplayName("[Success] 존재하지 않는 메뉴를 찾으면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "존재하지않는메뉴"})
    void checkIsNotExistMenu(String input) {
        assertThat(menus.hasSameMenu(input))
                .isFalse();
    }
}
