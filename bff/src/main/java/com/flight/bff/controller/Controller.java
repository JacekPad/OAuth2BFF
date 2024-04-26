package com.flight.bff.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/info")
    public Map<String, String> userInfo(OAuth2AuthenticationToken token) {
        Map<String, String> map = new HashMap<>();
        log.info("checking auth");
        map.put("name", token.getPrincipal().getName());
        return map;
    }

    @GetMapping("/api/public")
    public String publicTest() {
        return "this is public endpoint";
    }

    @PostMapping("post")
    public void testPost() {
        log.info("post works");
    }

    @GetMapping("/api/resources")
    public Map<String, String> resources(HttpServletRequest request, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(), oAuth2AuthenticationToken.getPrincipal().getName());
        String tokenValue = client.getAccessToken().getTokenValue();
        log.info("looking for resources");
        Map<String,String> resp = restTemplate.getForObject("http://localhost:8090/resources", Map.class);
        log.info("response: {}", resp);
        return resp;
    }
}
