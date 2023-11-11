package christmas.view;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.io.ByteArrayInputStream;
import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {
    @AfterEach
    void close() {
        Console.close();
    }

    @DisplayName("[Exception] 입력한 내용이 공백이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void getDateByNull(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readDate);
    }

    @DisplayName("[Exception] 입력한 내용이 숫자로 변경할 수 없는 값이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "1,2,3"})
    void getDateByNotNumber(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readDate);
    }

    @DisplayName("[Exception] 입력한 날짜가 1~31이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "32"})
    void getDateByOutOfBound(String input) {
        command(input);
        assertThatIllegalArgumentException()
                .isThrownBy(InputView::readDate);
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}
