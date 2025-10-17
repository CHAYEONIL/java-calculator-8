package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("빈 문자열 입력 시 0을 반환한다")
    void emptyString() {
        assertThat(calculator.calculate("")).isEqualTo(0);
    }

    @Test
    @DisplayName("null 입력 시 0을 반환한다")
    void nullString() {
        assertThat(calculator.calculate(null)).isEqualTo(0);
    }

    @Test
    @DisplayName("쉼표 구분자로 숫자를 더한다")
    void commaDelimiter() {
        assertThat(calculator.calculate("1,2")).isEqualTo(3);
        assertThat(calculator.calculate("1,2,3")).isEqualTo(6);
    }

    @Test
    @DisplayName("콜론 구분자로 숫자를 더한다")
    void colonDelimiter() {
        assertThat(calculator.calculate("1:2")).isEqualTo(3);
        assertThat(calculator.calculate("1:2:3")).isEqualTo(6);
    }

    @Test
    @DisplayName("쉼표와 콜론을 혼합하여 사용할 수 있다")
    void mixedDelimiter() {
        assertThat(calculator.calculate("1,2:3")).isEqualTo(6);
        assertThat(calculator.calculate("1:2,3:4")).isEqualTo(10);
    }

    @Test
    @DisplayName("커스텀 구분자를 사용할 수 있다")
    void customDelimiter() {
        assertThat(calculator.calculate("//;\n1;2;3")).isEqualTo(6);
        assertThat(calculator.calculate("//|\n1|2|3")).isEqualTo(6);
    }

    @Test
    @DisplayName("음수 입력 시 예외가 발생한다")
    void negativeNumber() {
        assertThatThrownBy(() -> calculator.calculate("1,-2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수");
    }

    @Test
    @DisplayName("숫자가 아닌 값 입력 시 예외가 발생한다")
    void invalidNumber() {
        assertThatThrownBy(() -> calculator.calculate("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닌");
    }

    @Test
    @DisplayName("잘못된 커스텀 구분자 형식 시 예외가 발생한다")
    void invalidCustomDelimiterFormat() {
        assertThatThrownBy(() -> calculator.calculate("//\n1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 커스텀 구분자");
    }
}