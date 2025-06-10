package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record Profile(
        String nickname,
        String profileImageUrl
) {
}
