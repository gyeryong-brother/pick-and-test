package com.gyeryongbrother.pickandtest.member.application.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.gyeryongbrother.pickandtest.member.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    ResponseEntity<RegisterMemberResponse> register(@RequestBody RegisterMemberRequest registerMemberRequest) {
        RegisterMemberCommand registerMemberCommand = registerMemberRequest.toCommand();
        RegisterMemberResponse registerMemberResponse = memberService.register(registerMemberCommand);
        return ResponseEntity.status(CREATED)
                .body(registerMemberResponse);
    }

    @GetMapping("/login")
    ResponseEntity<RegisterMemberResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginCommand loginCommand = loginRequest.toCommand();
        RegisterMemberResponse registerMemberResponse = memberService.login(loginCommand);
        return ResponseEntity.status(OK)
                .body(registerMemberResponse);
    }

    @GetMapping
    ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
