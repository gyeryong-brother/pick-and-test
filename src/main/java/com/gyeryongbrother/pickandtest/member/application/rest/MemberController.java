package com.gyeryongbrother.pickandtest.member.application.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<RegisterMemberResponse> register(@RequestBody RegisterMemberRequest registerMemberRequest) {
        RegisterMemberCommand registerMemberCommand = registerMemberRequest.toCommand();
        RegisterMemberResponse registerMemberResponse = memberService.register(registerMemberCommand);
        return ResponseEntity.status(CREATED)
                .body(registerMemberResponse);
    }

    @GetMapping("/{memberId}/portfolios")
    ResponseEntity<List<PortfolioResponse>> findAllPortfolios(@PathVariable Long memberId) {
        List<PortfolioResponse> portfolioResponses = memberService.findAllPortfolios(memberId);
        return ResponseEntity.ok(portfolioResponses);
    }
}
