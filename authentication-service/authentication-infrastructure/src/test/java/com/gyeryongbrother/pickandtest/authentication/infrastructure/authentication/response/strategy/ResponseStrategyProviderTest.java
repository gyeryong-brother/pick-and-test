package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.LOGIN_TYPE_NOT_SUPPORTED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ResponseStrategyProvider 는 LoginType 별로 ResponseStrategy 를 매핑해 관리하고 LoginType 에 맞는 Strategy 를 반환한다")
class ResponseStrategyProviderTest {

    private ResponseStrategy responseStrategy;
    private ResponseStrategyProvider responseStrategyProvider;

    @BeforeEach
    void setUp() {
        responseStrategy = new TestResponseStrategy();
        responseStrategyProvider = new ResponseStrategyProvider(Set.of(responseStrategy));
    }

    @Test
    @DisplayName("LoginType 으로 Strategy 를 가져온다")
    void getStrategyByType() {
        // when
        ResponseStrategy result = responseStrategyProvider.getStrategyByType(LoginType.TEST_SUPPORTED);

        // then
        assertThat(result).isEqualTo(responseStrategy);
    }

    @Test
    @DisplayName("지원하지 않는 LoginType 으로 Strategy 를 가져오라하면 예외가 발생한다")
    void getStrategyByUnsupportedType() {
        // when
        BaseExceptionType result = assertThrows(AuthenticationInfrastructureException.class, () ->
                responseStrategyProvider.getStrategyByType(LoginType.TEST_UNSUPPORTED)
        ).exceptionType();

        // then
        assertThat(result).isEqualTo(LOGIN_TYPE_NOT_SUPPORTED);
    }
}
