package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoClientTest {

    @Autowired
    private KakaoClient kakaoClient;

    @Test
    void fetchMember() {
        // given

        // when
        OauthMember oauthMember = kakaoClient.fetchMember(
                "rXI2RTGCVDZ5_SWq-yapC7EGn0DhRbivtP3s6QLENk8Qg_dDTED6fQAAAAQKDRlTAAABl3bxWTBDz1szkZmFRA");

        // then
        System.out.println(oauthMember);
    }
}
