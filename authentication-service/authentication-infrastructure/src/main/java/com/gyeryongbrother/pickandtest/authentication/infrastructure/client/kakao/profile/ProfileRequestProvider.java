package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType.KAKAO_PROFILE;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.AuthFormRequest;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Request;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestContext;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;
import org.springframework.stereotype.Component;

@Component
public class ProfileRequestProvider implements RequestProvider {

    private static final String TOKEN = "token";

    @Override
    public RequestType support() {
        return KAKAO_PROFILE;
    }

    @Override
    public Request provide(RequestContext context) {
        String token = context.get(TOKEN);
        return new AuthFormRequest(token);
    }
}
