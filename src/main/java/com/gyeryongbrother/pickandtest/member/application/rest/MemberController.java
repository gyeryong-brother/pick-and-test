package com.gyeryongbrother.pickandtest.member.application.rest;

import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

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
}
