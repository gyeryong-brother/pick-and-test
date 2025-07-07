package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.PasswordEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@DisplayName("비밀번호 인크립터를 구현한다")
class PasswordEncryptorImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    private PasswordEncryptor passwordEncryptor;

    @BeforeEach
    void setUp() {
        passwordEncryptor = new PasswordEncryptorImpl(passwordEncoder);
    }

    @Test
    @DisplayName("비밀번호를 암호화한다")
    void encrypt() {
        // given
        given(passwordEncoder.encode(anyString()))
                .willReturn("encodedPassword");
        String password = "password";

        // when
        String result = passwordEncryptor.encrypt(password);

        // then
        assertThat(result).isEqualTo("encodedPassword");
    }
}
