package lotto;

import lotto.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidatorProcessorTest {

    private ValidatorProcessor validatorProcessor;

    @TestFactory
    @DisplayName("ValidatorProcessor validateMoney Test")
    Stream<DynamicTest> ValidatorProcessorValidateMoneyTest() {
        validatorProcessor = new ValidatorProcessorImpl();

        return Stream.of(
                DynamicTest.dynamicTest("금액이 나눠떨어지는 경우 통과", () -> {
                    final Integer money = 1000;
                    validatorProcessor.validateMoney(money, PriceEnum.LOTTO_PRICE);
                }),
                DynamicTest.dynamicTest("금액이 나눠떨어지지 않는경우 예외를 던져준다.", () -> {
                    final Integer money = 500;

                    assertThatThrownBy(() -> validatorProcessor.validateMoney(money, PriceEnum.LOTTO_PRICE))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("금액 단위를 일치시켜야 합니다.");
                }),
                DynamicTest.dynamicTest("금액이 나눠떨어지지 않는경우 예외를 던져준다.", () -> {
                    final Integer money = 1500;

                    assertThatThrownBy(() -> validatorProcessor.validateMoney(money, PriceEnum.LOTTO_PRICE))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessageContaining("금액 단위를 일치시켜야 합니다.");
                })
        );
    }
}
