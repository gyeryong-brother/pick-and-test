package com.gyeryongbrother.pickandtest.member.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
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
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberQueryRepository memberQueryRepository;

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
}
