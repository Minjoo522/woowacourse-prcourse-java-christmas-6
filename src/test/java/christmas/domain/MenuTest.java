package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    void init() {
        menu = new Menu("dessert", "초코케이크", "15000");
    }

    @DisplayName("[Success] 동일한 메뉴 이름이면 true를 리턴하고, 다른 이름이면 false를 리턴한다")
    @ParameterizedTest
    @CsvSource(value = {"초코케이크:true", "양송이수프:false"}, delimiter = ':')
    void checkSameName(String name, boolean expected) {
        assertThat(menu.isSameName(name))
                .isEqualTo(expected);
    }

    @DisplayName("[Success] 동일한 메뉴 타입이면 true를 리턴하고, 다른 타입이면 false를 리턴한다")
    @ParameterizedTest
    @CsvSource(value = {"dessert:true", "main:false"}, delimiter = ':')
    void checkSameType(String name, boolean expected) {
        assertThat(menu.isSameType(name))
                .isEqualTo(expected);
    }
}
