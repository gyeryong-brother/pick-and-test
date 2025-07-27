package com.gyeryongbrother.pickandtest.authentication.acceptance;

import static com.gyeryongbrother.pickandtest.authentication.acceptance.dto.LoginRequestFixture.loginRequest;
import static com.gyeryongbrother.pickandtest.authentication.acceptance.dto.RegisterRequestFixture.registerRequest;
import static com.gyeryongbrother.pickandtest.authentication.acceptance.dto.RegisterResponseFixture.registerResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.authentication.application.dto.RegisterRequest;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.usernamepassword.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy.dto.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("인증 api 를 제공한다")
@Sql("/truncate.sql")
public class AuthenticationAcceptanceTest {

    @LocalServerPort
    private int port;

    @MockBean
    private MemberClient memberClient;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("회원가입을 진행한다")
    void registerMember() {
        // given
        RegisterResponse expected = registerResponse();

        // when
        RegisterResponse result = register();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private RegisterResponse register() {
        RegisterRequest request = registerRequest();
        when(memberClient.registerMember(any()))
                .thenReturn(new Member(1L, "odo27", "profileImageUrl"));
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/authentication-service/auth/register")
                .then().log().all()
                .extract();
        return response.as(RegisterResponse.class);
    }

    @Test
    @DisplayName("로그인을 진행한다")
    void loginByUsernameAndPassword() {
        // given
        register();

        // when
        ExtractableResponse<Response> response = login();
        LoginResponse result = response.as(LoginResponse.class);
        String refreshToken = response.cookie("refresh-token");

        // then
        assertAll(
                () -> assertThat(result.accessToken()).isNotNull(),
                () -> assertThat(refreshToken).isNotNull()
        );
    }

    private ExtractableResponse<Response> login() {
        LoginRequest request = loginRequest();
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/authentication-service/login")
                .then().log().all()
                .extract();
    }

    @Test
    @DisplayName("로그아웃을 진행한다")
    void logout() {
        // given
        register();
        ExtractableResponse<Response> loginResponse = login();
        String refreshToken = loginResponse.cookie("refresh-token");

        // when
        ExtractableResponse<Response> response = logout(refreshToken);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> logout(String refreshToken) {
        return RestAssured.given().log().all()
                .cookie("refresh-token", refreshToken)
                .contentType(ContentType.JSON)
                .when().post("/authentication-service/auth/logout")
                .then().log().all()
                .extract();
    }
}
