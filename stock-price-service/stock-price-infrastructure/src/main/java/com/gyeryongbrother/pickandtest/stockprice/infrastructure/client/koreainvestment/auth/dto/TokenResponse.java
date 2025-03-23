package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TokenResponse(
        @JsonProperty(value = "access_token")
        String accessToken,
        @JsonProperty(value = "access_token_token_expired")
        String accessTokenExpired
) {
}
