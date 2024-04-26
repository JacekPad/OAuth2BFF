package com.example.bffGateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class Controller {

    @GetMapping("logout")
    public void shti() {
        log.info("LOGOUT?");
    }

    @GetMapping("/info")
    public Map<String, String> getInfo(Authentication authentication) {
        log.info("checking user info");
        Map<String, String> map = new HashMap<>();
        if (authentication == null) {
            map.put("name", "guest");
        } else {
            map.put("name", authentication.getName());
        }
        log.info("user: {}",map.get("name"));
        return map;
    }

    @PostMapping("/post")
    public void testingPost() {
        log.info("doing post stuff");
    }
}
