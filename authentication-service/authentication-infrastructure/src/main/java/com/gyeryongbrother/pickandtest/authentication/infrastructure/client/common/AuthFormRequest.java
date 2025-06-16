package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import java.util.Optional;
import org.springframework.http.MediaType;

public class AuthFormRequest extends Request {

    private final String token;

    public AuthFormRequest(String token) {
        this.token = token;
    }

    @Override
    protected Optional<String> token() {
        return Optional.of(token);
    }

    @Override
    protected Optional<MediaType> mediaType() {
        return Optional.of(APPLICATION_FORM_URLENCODED);
    }
}
