package com.gyeryongbrother.pickandtest.authentication.acceptance;

import static com.gyeryongbrother.pickandtest.authentication.acceptance.dto.RegisterRequestFixture.registerRequest;
import static com.gyeryongbrother.pickandtest.authentication.acceptance.dto.RegisterResponseFixture.registerResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.authentication.application.dto.RegisterRequest;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
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
    void register() {
        // given
        RegisterRequest request = registerRequest();
        RegisterResponse expected = registerResponse();
        when(memberClient.registerMember(any()))
                .thenReturn(new Member(1L, "odo27", "profileImageUrl"));

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/authentication-service/auth/register")
                .then().log().all()
                .extract();
        RegisterResponse result = response.as(RegisterResponse.class);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
