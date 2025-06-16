package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import java.util.Optional;
import org.springframework.http.MediaType;

public class FormRequest extends Request {

    public FormRequest(RequestBody requestBody) {
        super(requestBody);
    }

    @Override
    protected Optional<String> token() {
        return Optional.empty();
    }

    @Override
    protected Optional<MediaType> mediaType() {
        return Optional.of(APPLICATION_FORM_URLENCODED);
    }
}
