package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptorImpl implements PasswordEncryptor {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
