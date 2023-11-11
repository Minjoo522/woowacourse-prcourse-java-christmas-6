package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    @DisplayName("[Exception] 입력한 날짜가 1~31이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void createOrderByNull(int input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Order(input));
    }
}
