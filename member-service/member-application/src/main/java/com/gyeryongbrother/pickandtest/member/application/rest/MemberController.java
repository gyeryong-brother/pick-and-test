package com.gyeryongbrother.pickandtest.member.application.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.gyeryongbrother.pickandtest.member.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.member.application.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LogoutResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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

    @PostMapping
    ResponseEntity<RegisterMemberResponse> register(@RequestBody RegisterMemberRequest registerMemberRequest) {
        RegisterMemberCommand registerMemberCommand = registerMemberRequest.toCommand();
        RegisterMemberResponse registerMemberResponse = memberService.register(registerMemberCommand);
        return ResponseEntity.status(CREATED)
                .body(registerMemberResponse);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginCommand loginCommand = loginRequest.toCommand();
        LoginResponse loginResponse = memberService.login(loginCommand);

        Cookie refreshTokenCookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.status(OK)
                .body(loginResponse);
    }

    @PostMapping("/logout")
    ResponseEntity<LogoutResponse> logout(@CookieValue(name = "refreshToken", required = false) String refreshToken,
                                          HttpServletResponse response) {
        LogoutResponse logoutResponse = memberService.logout(refreshToken);
        Cookie refreshCookie = new Cookie("refreshToken", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0);
        response.addCookie(refreshCookie);
        return ResponseEntity.status(OK).body(logoutResponse);
    }

    @GetMapping
    ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
