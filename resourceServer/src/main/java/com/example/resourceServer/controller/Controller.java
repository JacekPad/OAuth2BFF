package com.example.resourceServer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class Controller {

    @GetMapping("/public")
    public Map<String, String> publicResource() {
        log.info("accessing public resources");
        Map<String, String> map = new HashMap<>();
        map.put("name","this is public resource");
        return map;
    }

    @GetMapping("/resources")
    public Map<String, String> resource(Authentication authentication) {
        log.info("accessing protected resources");
        log.info("Authentication: {}", authentication.getAuthorities());
        Map<String, String> map = new HashMap<>();
        map.put("name", "this is protected resource");
        return map;
    }
}
