package com.gyeryongbrother.pickandtest.noticeboard.domain.service.dto;

import java.util.List;

public record PostsResponse(
        List<SimplePostResponse> simplePostResponses
) {
}
