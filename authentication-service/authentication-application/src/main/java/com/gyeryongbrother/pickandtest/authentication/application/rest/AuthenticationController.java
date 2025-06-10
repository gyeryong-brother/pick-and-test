package com.gyeryongbrother.pickandtest.authentication.application.rest;

import static org.springframework.http.HttpStatus.OK;

import com.gyeryongbrother.pickandtest.authentication.application.dto.LoginRequest;
import com.gyeryongbrother.pickandtest.authentication.application.dto.OauthLoginRequest;
import com.gyeryongbrother.pickandtest.authentication.application.dto.RegisterRequest;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginPageResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.OauthLoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationQueryService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final AuthenticationQueryService authenticationQueryService;
    private final CookieManager cookieManager;

    @PostMapping("/register")
    ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        return null;
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginCommand loginCommand = loginRequest.toCommand();
        LoginResponse loginResponse = authenticationService.login(loginCommand);
        cookieManager.setCookie(response, loginResponse.refreshToken());
        return ResponseEntity.status(OK)
                .body(loginResponse);
    }

    @SneakyThrows
    @GetMapping("/{authenticationMethod}")
    ResponseEntity<Void> oauthLoginPage(
            @PathVariable AuthenticationMethod authenticationMethod,
            HttpServletResponse response
    ) {
        LoginPageResponse loginPage = authenticationQueryService.getLoginPage(authenticationMethod);
        response.sendRedirect(loginPage.url());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login/{authenticationMethod}")
    ResponseEntity<LoginResponse> login(
            @PathVariable AuthenticationMethod authenticationMethod,
            @RequestBody OauthLoginRequest request
    ) {
        OauthLoginCommand command = request.toCommand(authenticationMethod);
        LoginResponse response = authenticationService.oauthLogin(command);
        return ResponseEntity.ok(response);
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
