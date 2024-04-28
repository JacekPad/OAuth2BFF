package com.example.bffGateway.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public User getInfo(Authentication authentication) {
        log.info("checking user info");
        User user = new User();
        Map<String, String> map = new HashMap<>();
        if (authentication == null) {
            user.setName("guest");
            user.setAuthenticated(false);
        } else {
            user.setName(authentication.getName());
            user.setAuthenticated(true);
        }
        log.info("user: {}",user.getName());
        return user;
    }

    @PostMapping("/post")
    public void testingPost() {
        log.info("doing post stuff");
    }
}


@Data
class User {
    private String name;
    private boolean authenticated;
}
