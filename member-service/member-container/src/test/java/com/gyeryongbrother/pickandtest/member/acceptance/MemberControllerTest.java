package com.gyeryongbrother.pickandtest.member.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.member.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.JwtUtil;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("회원 api 를 제공한다")
class MemberControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MemberJpaRepository memberJpaRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("회원가입을 진행한다")
    void register() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name", "userId", "password");

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(registerMemberRequest)
                .when().post("/members/register")
                .then().log().all()
                .extract();

        RegisterMemberResponse result = response.as(RegisterMemberResponse.class);
        String accessToken = result.accessToken();
        String refreshToken = result.refreshToken();
        UserRole role = jwtUtil.getRoleFromToken(accessToken);
        Long memberIdfromAccess = jwtUtil.getMemberIdFromToken(accessToken);
        MemberEntity memberEntity = memberJpaRepository.findById(1L).orElseThrow();
        String expectedRefreshToken = memberEntity.getRefreshToken();

        // then
        assertAll(
                () -> assertThat(role).isEqualTo(UserRole.ROLE_USER),
                () -> assertThat(memberIdfromAccess).isEqualTo(1L),
                () -> assertThat(refreshToken).isEqualTo(expectedRefreshToken)
        );
    }

    @Test
    @DisplayName("로그인을 시도한다")
    void login() {
        //given
        Member member = Member.builder()
                .userId("userId")
                .password("password")
                .build();

        memberRepository.save(member);

        LoginRequest loginRequest = new LoginRequest("userId", "password");

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().get("/members/login")
                .then().log().all()
                .extract();

        RegisterMemberResponse result = response.as(RegisterMemberResponse.class);
        String accessToken = result.accessToken();
        String refreshToken = result.refreshToken();
        UserRole role = jwtUtil.getRoleFromToken(accessToken);
        Long memberIdfromAccess = jwtUtil.getMemberIdFromToken(accessToken);
        MemberEntity memberEntity = memberJpaRepository.findById(1L).orElseThrow();
        String expectedRefreshToken = memberEntity.getRefreshToken();

        //then
        assertAll(
                () -> assertThat(role).isEqualTo(UserRole.ROLE_USER),
                () -> assertThat(memberIdfromAccess).isEqualTo(1L),
                () -> assertThat(refreshToken).isEqualTo(expectedRefreshToken)
        );
    }
}
