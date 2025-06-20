package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

public interface PasswordEncryptor {

    String encrypt(String password);
}
