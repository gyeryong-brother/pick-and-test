package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginResponse(
        String accessToken,
        String refreshToken
) {

}
