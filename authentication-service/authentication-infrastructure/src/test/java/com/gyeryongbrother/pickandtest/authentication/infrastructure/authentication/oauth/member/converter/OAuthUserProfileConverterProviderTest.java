package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OAuthUserProfileConverterProviderTest {

    private OAuthUserProfileConverterProvider provider;

    @Test
    @DisplayName("OAuth 타입으로 변환기를 가져온다")
    void convert() {
        // given
        OAuthUserProfileConverter converter1 = mock(OAuthUserProfileConverter.class);
        OAuthUserProfileConverter converter2 = mock(OAuthUserProfileConverter.class);
        provider = new OAuthUserProfileConverterProvider(Set.of(
                converter1, converter2
        ));
        OAuthType oAuthType1 = mock(OAuthType.class);
        OAuthType oAuthType2 = mock(OAuthType.class);
        given(converter1.getSupportedOAuthType())
                .willReturn(oAuthType1);
        given(converter2.getSupportedOAuthType())
                .willReturn(oAuthType2);

        // when
        OAuthUserProfileConverter result = provider.getConverterByType(oAuthType1);

        // then
        assertThat(result).isEqualTo(converter1);
    }
}
