package com.gyeryongbrother.pickandtest.authentication.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.gyeryongbrother.pickandtest.authentication.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.authentication.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.logout.LogoutResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("인증 api 를 제공한다")
@Sql("/truncate.sql")
public class AuthenticationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private RefreshTokenQueryRepository refreshTokenQueryRepository;

    @Autowired
    private UsernamePasswordCredentialRepository usernamePasswordCredentialRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("로그인을 시도한다")
    void login() {
        //given
        register("usernameLogin");
        LoginRequest loginRequest = new LoginRequest("usernameLogin", "password");

        //when
        ExtractableResponse<Response> response = login(loginRequest);
        LoginResponse result = response.as(LoginResponse.class);
        String accessToken = result.accessToken();
        String refreshToken = result.refreshToken();
        MemberRole role = jwtProvider.getRoleFromToken(accessToken);
        Long memberIdfromAccess = jwtProvider.getMemberIdFromToken(accessToken);
        List<RefreshToken> refreshTokens = refreshTokenQueryRepository.findByMemberId(1L);
        String expectedRefreshToken = refreshTokens.get(0).token();

        //then
        assertAll(
                () -> assertThat(role).isEqualTo(MemberRole.USER),
                () -> assertThat(memberIdfromAccess).isEqualTo(1L),
                () -> assertThat(refreshToken).isEqualTo(expectedRefreshToken)
        );
    }

    private void register(String username) {
        UsernamePasswordCredential usernamePasswordCredential = new UsernamePasswordCredential(
                null,
                1L,
                MemberRole.USER,
                username,
                passwordEncoder.encode("password")
        );
        usernamePasswordCredentialRepository.save(usernamePasswordCredential);
    }

    private String encode(String password) {
        return passwordEncoder.encode(password);
    }

    private ExtractableResponse<Response> login(LoginRequest loginRequest) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post("/authentication-service/auth/login")
                .then().log().all()
                .extract();
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 로그인")
    void loginWithNonExistedUserId() {
        //given
        LoginRequest loginRequest = new LoginRequest("userId0", "password");
        ErrorResponse expected = new ErrorResponse("사용자 정보가 일치하지 않습니다.");

        //when
        ExtractableResponse<Response> response = login(loginRequest);
        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("잘못된 비밀번호로 로그인")
    void loginWithIncrrectPassword() {
        //given
        register("userId1");
        LoginRequest loginRequest = new LoginRequest("userId1", "password0");
        ErrorResponse expected = new ErrorResponse("사용자 정보가 일치하지 않습니다.");

        //when
        ExtractableResponse<Response> response = login(loginRequest);
        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("로그아웃을 한다")
    void logout() {
        //given
        register("usernameLogout");
        LoginRequest loginRequest = new LoginRequest("usernameLogout", "password");
        ExtractableResponse<Response> response0 = login(loginRequest);
        LoginResponse result0 = response0.as(LoginResponse.class);
        String accessToken = result0.accessToken();
        String refreshToken = result0.refreshToken();

        //when
        ExtractableResponse<Response> result = logout(accessToken, refreshToken);

        //then
        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> logout(String accessToken, String refreshToken) {
        return RestAssured.given()
                .log().all()
                .cookie("refresh-token", refreshToken)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when()
                .post("/authentication-service/auth/logout")
                .then()
                .log().all()
                .extract();
    }

    @Test
    @DisplayName("잘못된 access 토큰으로 로그아웃을 시도한다")
    void logoutWitInvalidAccessToken() {
        //given
        register("usernameLogoutWithInvalidAccessToken");
        LoginRequest loginRequest = new LoginRequest("usernameLogoutWithInvalidAccessToken", "password");
        ExtractableResponse<Response> response0 = login(loginRequest);
        LoginResponse result0 = response0.as(LoginResponse.class);
        String refreshToken = result0.refreshToken();
        String invalidAccessToken = "invalidAccessToken";

        //when
        ExtractableResponse<Response> response = logout(invalidAccessToken, refreshToken);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("잘못된 refresh Token으로 로그아웃 시도")
    void logoutWithInvalidRefreshToken() {
        //given
        register("usernameLogoutWithInvalidRefreshToken");
        LoginRequest loginRequest = new LoginRequest("usernameLogoutWithInvalidRefreshToken", "password");
        ExtractableResponse<Response> response0 = login(loginRequest);
        LoginResponse result0 = response0.as(LoginResponse.class);
        String accessToken = result0.accessToken();

        String invalidRefreshToken = "invalidRefreshToken";
        ErrorResponse expected = new ErrorResponse("Invalid Refresh Token Error");

        //when
        ExtractableResponse<Response> response = logout(accessToken, invalidRefreshToken);
        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("Access 토큰 없이 로그아웃 시도")
    void logoutWithoutAccessToken() {
        //given
        register("usernameLogoutWithoutAccessToken");
        LoginRequest loginRequest = new LoginRequest("usernameLogoutWithoutAccessToken", "password");
        ExtractableResponse<Response> response0 = login(loginRequest);
        LoginResponse result0 = response0.as(LoginResponse.class);
        String refreshToken = result0.refreshToken();

        //when
        ExtractableResponse<Response> response = logout(refreshToken);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> logout(String refreshToken) {
        return RestAssured.given()
                .log().all()
                .cookie("refresh-token", refreshToken)
                .contentType(ContentType.JSON)
                .when()
                .post("/authentication-service/auth/logout")
                .then()
                .log().all()
                .extract();
    }

    @Test
    @DisplayName("Refresh Token 없이 로그아웃 시도")
    void logoutWithoutRefreshToken() {
        //given
        register("usernameLogoutWithoutRefreshToken");
        LoginRequest loginRequest = new LoginRequest("usernameLogoutWithoutRefreshToken", "password");
        ExtractableResponse<Response> response0 = login(loginRequest);
        LoginResponse result0 = response0.as(LoginResponse.class);
        String accessToken = result0.accessToken();
        ErrorResponse expected = new ErrorResponse("리프레시 토큰이 필요합니다.");

        //when
        ExtractableResponse<Response> response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when()
                .post("/authentication-service/auth/logout")
                .then()
                .log().all()
                .extract();
        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("잘못된 access 토큰과 refresh 토큰으로 로그아웃 시도")
    void logoutWithInvalidAccessTokenAndRefreshToken() {
        //given
        String invalidAccessToken = "invalidAccessToken";
        String invalidRefreshToken = "invalidRefreshToken";
        ErrorResponse expected = new ErrorResponse("Invalid Token Error");

        //when
        ExtractableResponse<Response> response = logout(invalidAccessToken, invalidRefreshToken);
        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(UNAUTHORIZED.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("access 토큰과 refresh 토큰 없이 로그아웃 시도")
    void logoutWithoutAccessTokenAndRefreshToken() {
        //given
        ErrorResponse expected = new ErrorResponse("Invalid Token Error");

        //when
        ExtractableResponse<Response> response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post("/authentication-service/auth/logout")
                .then()
                .log().all()
                .extract();

        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(UNAUTHORIZED.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("만료된 accessToken으로 로그아웃 시도")
    void logoutWithExpiredAccessToken() throws InterruptedException {
        //given
        register("usernameLogoutWithExpiredAccessToken");
        LoginRequest loginRequest = new LoginRequest("usernameLogoutWithExpiredAccessToken", "password");
        ExtractableResponse<Response> response0 = login(loginRequest);
        LoginResponse result0 = response0.as(LoginResponse.class);
        String accessToken = result0.accessToken();
        String refreshToken = result0.refreshToken();
        LogoutResponse expected = new LogoutResponse(1L);

        //when
        Thread.sleep(2000L);
        ExtractableResponse<Response> response = logout(accessToken, refreshToken);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
