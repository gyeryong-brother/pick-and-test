package com.gyeryongbrother.pickandtest.authentication.application.rest;

import static org.springframework.http.HttpStatus.OK;

import com.gyeryongbrother.pickandtest.authentication.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final CookieManager cookieManager;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginCommand loginCommand = loginRequest.toCommand();
        LoginResponse loginResponse = authenticationService.login(loginCommand);
        cookieManager.setCookie(response, loginResponse.refreshToken());
        return ResponseEntity.status(OK)
                .body(loginResponse);
    }

    @PostMapping("/login/{oauthType}")
    ResponseEntity<LoginResponse> login(@PathVariable String oauthType) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    ResponseEntity<Void> logout(
            @CookieValue(name = "refreshToken") String refreshToken,
            HttpServletResponse response
    ) {
        authenticationService.logout(refreshToken);
        Cookie refreshCookie = new Cookie("refreshToken", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0);
        response.addCookie(refreshCookie);
        return ResponseEntity.ok().build();
    }
}
