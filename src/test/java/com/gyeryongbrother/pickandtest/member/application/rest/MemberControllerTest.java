package com.gyeryongbrother.pickandtest.member.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("회원 api 를 제공한다")
class MemberControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("회원가입을 진행한다")
    void register() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name");

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(registerMemberRequest)
                .when().post("/members")
                .then().log().all()
                .extract();
        RegisterMemberResponse result = response.as(RegisterMemberResponse.class);

        // then
        assertAll(
                () -> assertThat(result.id()).isPositive(),
                () -> assertThat(result.name()).isEqualTo("name")
        );
    }
}
