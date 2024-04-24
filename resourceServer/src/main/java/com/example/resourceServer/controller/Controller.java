package com.example.resourceServer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {

    @GetMapping("/public")
    public String publicResource() {
        return "this is public resource";
    }

    @GetMapping("/resources")
    public String resource(Authentication authentication) {
        if (authentication != null) {
            log.info("Authentication: {}", authentication.getAuthorities());
        }
        return "this is protected resource";
    }
}
