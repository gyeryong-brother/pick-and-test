package com.gyeryongbrother.pickandtest.member.application.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.gyeryongbrother.pickandtest.common.authorizationresolver.Guest;
import com.gyeryongbrother.pickandtest.common.authorizationresolver.MemberAuthority;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    ResponseEntity<RegisterMemberResponse> register(@RequestBody RegisterMemberRequest request) {
        RegisterMemberCommand command = request.toCommand();
        RegisterMemberResponse registerMemberResponse = memberService.register(command);
        return ResponseEntity.status(CREATED)
                .body(registerMemberResponse);
    }

    @GetMapping
    ResponseEntity<String> test(
            @Guest MemberAuthority memberAuthority
    ) {
        log.info("X-Member-Id: {}", memberAuthority.memberId());
        log.info("X-Member-Roles: {}", memberAuthority.memberRoles());
        return ResponseEntity.ok("test!");
    }
}
