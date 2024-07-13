package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenRequest(
        @JsonProperty(value = "grant_type")
        String grantType,
        @JsonProperty(value = "appkey")
        String appKey,
        @JsonProperty(value = "appsecret")
        String appSecret
) {
}
