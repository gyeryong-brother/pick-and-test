package com.gyeryongbrother.pickandtest.member.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.math.BigDecimal;
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

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
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

    @Test
    @DisplayName("멤버 아이디로 해당 멤버의 모든 포트폴리오를 조회한다")
    void findAllPortfolios(){
        //given
        Member member1 = Member.builder().build();
        Member member2 = Member.builder().build();
        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

        Portfolio portfolio1 = Portfolio.builder()
                .memberId(savedMember1.getId())
                .build();
        Portfolio portfolio2 = Portfolio.builder()
                .memberId(savedMember2.getId())
                .build();
        Portfolio portfolio3 = Portfolio.builder()
                .memberId(savedMember1.getId())
                .build();
        Portfolio savedPortfolio1=portfolioRepository.save(portfolio1);
        Portfolio savedPortfolio2=portfolioRepository.save(portfolio2);
        Portfolio savedPortfolio3=portfolioRepository.save(portfolio3);

        List<PortfolioResponse> expected = List.of(savedPortfolio1, savedPortfolio3).stream()
                .map(PortfolioResponse::from)
                .toList();

        //when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/members/{memberId}/portfolios", savedMember1.getId())
                .then().log().all()
                .extract();
        List<PortfolioResponse> result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
