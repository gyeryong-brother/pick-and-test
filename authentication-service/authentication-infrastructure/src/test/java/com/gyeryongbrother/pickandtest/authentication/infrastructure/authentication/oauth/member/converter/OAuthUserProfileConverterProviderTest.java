package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import static com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType.TEST_SUPPORTED;
import static com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType.TEST_UNSUPPORTED;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("OAuthUserProfileConverterProvider 는 OAuthType 별로 OAuthUserProfileConverter 를 매핑해 관리하고 OAuthType 에 맞는 Converter 를 반환한다")
class OAuthUserProfileConverterProviderTest {

    private OAuthUserProfileConverter oAuthUserProfileConverter;
    private OAuthUserProfileConverterProvider profileConverterProvider;

    @BeforeEach
    void setUp() {
        oAuthUserProfileConverter = new TestOAuthUserProfileConverter();
        profileConverterProvider = new OAuthUserProfileConverterProvider(Set.of(
                oAuthUserProfileConverter
        ));
    }

    @Test
    @DisplayName("OAuthType 으로 Converter 를 가져온다")
    void getConverterByType() {
        // when
        OAuthUserProfileConverter result = profileConverterProvider.getConverterByType(TEST_SUPPORTED);

        // then
        assertThat(result).isEqualTo(oAuthUserProfileConverter);
    }

    @Test
    @DisplayName("지원하지 않는 OAuthType 으로 Converter 를 가져오려하면 예외가 발생한다")
    void getConverterByUnsupportedType() {
        // when
        BaseExceptionType result = Assertions.assertThrows(AuthenticationInfrastructureException.class, () ->
                profileConverterProvider.getConverterByType(TEST_UNSUPPORTED)
        ).exceptionType();

        // then
        assertThat(result).isEqualTo(OAUTH_SERVER_NOT_SUPPORTED);
    }
}
