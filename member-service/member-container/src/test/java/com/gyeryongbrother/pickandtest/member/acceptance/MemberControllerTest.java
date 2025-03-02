package com.gyeryongbrother.pickandtest.member.acceptance;

import static com.gyeryongbrother.pickandtest.member.domain.core.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.gyeryongbrother.pickandtest.member.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.JwtUtil;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenQueryRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
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
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private RefreshTokenQueryRepository refreshTokenQueryRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("회원가입을 진행한다")
    void register() {
        // given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name", "username", "password");
        RegisterMemberResponse expected = new RegisterMemberResponse("name");

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(registerMemberRequest)
                .when().post("/members")
                .then().log().all()
                .extract();

        RegisterMemberResponse result = response.as(RegisterMemberResponse.class);

        // then
        assertThat(result.equals(expected));
    }

    @Test
    @DisplayName("이미 존재하는 아이디로 회원가입")
    void registerWithExistedUserId() {
        //given
        Member member = Member.builder()
                .username("username-1")
                .password("lol")
                .build();
        memberRepository.save(member);
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name", "username-1", "password");
        ErrorResponse expected = new ErrorResponse("이미 존재하는 아이디입니다.");

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(registerMemberRequest)
                .when().post("/members")
                .then().log().all()
                .extract();

        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }

    @Test
    @DisplayName("로그인을 시도한다")
    void login() {
        //given
        RegisterMemberRequest registerMemberRequest = new RegisterMemberRequest("name", "usernameLogin", "password");
        memberService.register(registerMemberRequest.toCommand());

        LoginRequest loginRequest = new LoginRequest("usernameLogin", "password");

        Member member = memberQueryRepository.findByUsername("usernameLogin");

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post("/members/login")
                .then().log().all()
                .extract();

        LoginResponse result = response.as(LoginResponse.class);
        String accessToken = result.accessToken();
        String refreshToken = result.refreshToken();
        UserRole role = jwtUtil.getRoleFromToken(accessToken);
        Long memberIdfromAccess = jwtUtil.getMemberIdFromToken(accessToken);
        String expectedRefreshToken = refreshTokenQueryRepository.findByUsername(member.getUsername()).get(0)
                .getRefreshToken();

        //then
        assertAll(
                () -> assertThat(role).isEqualTo(ROLE_USER),
                () -> assertThat(memberIdfromAccess).isEqualTo(member.getId()),
                () -> assertThat(refreshToken).isEqualTo(expectedRefreshToken)
        );
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 로그인")
    void loginWithNonExistedUserId() {
        //given
        LoginRequest loginRequest = new LoginRequest("userId0", "password");
        ErrorResponse expected = new ErrorResponse("사용자 정보가 일치하지 않습니다.");

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post("/members/login")
                .then().log().all()
                .extract();

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
        Member member = Member.builder()
                .username("userId1")
                .password("password")
                .build();

        memberRepository.save(member);

        LoginRequest loginRequest = new LoginRequest("userId1", "password0");
        ErrorResponse expected = new ErrorResponse("사용자 정보가 일치하지 않습니다.");

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post("/members/login")
                .then().log().all()
                .extract();

        ErrorResponse result = response.as(ErrorResponse.class);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(NOT_FOUND.value()),
                () -> assertThat(result).isEqualTo(expected)
        );
    }
}
