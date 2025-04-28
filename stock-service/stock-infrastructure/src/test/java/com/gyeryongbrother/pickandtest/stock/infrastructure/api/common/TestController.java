package com.gyeryongbrother.pickandtest.stock.infrastructure.api.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ok")
    ResponseEntity<String> ok() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/bad-request")
    ResponseEntity<String> badRequest() {
        return ResponseEntity.badRequest().body("bad request");
    }

    @GetMapping("/internal-server-error")
    ResponseEntity<String> internalServerError() {
        return ResponseEntity.internalServerError().body("internal server error");
    }
}
