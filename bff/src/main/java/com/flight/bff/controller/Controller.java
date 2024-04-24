package com.flight.bff.controller;

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

    @GetMapping("/info")
    public Map<String, String> userInfo(Authentication authentication) {
        Map<String, String> map = new HashMap<>();
        log.info("checking auth");
        if (authentication == null) {
            map.put("name","guest");
        } else {
            map.put("name", authentication.getName());
        }
        return map;
    }

    @GetMapping("/public")
    public String publicTest() {
        return "this is public endpoint";
    }

    @PostMapping("post")
    public void testPost() {
        log.info("post works");
    }
}
