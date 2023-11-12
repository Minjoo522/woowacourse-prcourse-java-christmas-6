package christmas.service;

import static christmas.config.Config.MENU_CSV_PATH;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.Menu;
import christmas.domain.Menus;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {
    Menus menus;

    @BeforeEach
    void init() {
        List<Menu> rawMenus = CSVReader.getMenus(MENU_CSV_PATH.getValue());
        menus = new Menus(rawMenus);
    }

    @DisplayName("[Exception] 메뉴 형식이 다르면 예외가 발생한다.")
    @Test
    void parseOrderByInvalidForm() {
        List<String> input = List.of("양송이수프,2");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Parser.parseOrder(input, menus));
    }

    @DisplayName("[Exception] 메뉴판에 존재하지 않는 메뉴를 입력하면 예외가 발생한다.")
    @Test
    void parseOrderByNonExistentMenu() {
        List<String> input = List.of("콜라-2");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Parser.parseOrder(input, menus));
    }

    @DisplayName("[Exception] 중복된 메뉴를 입력하면 예외가 발생한다.")
    @Test
    void parseOrderByDuplicateMenu() {
        List<String> input = List.of("양송이수프-1", "양송이수프-2");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Parser.parseOrder(input, menus));
    }
}
