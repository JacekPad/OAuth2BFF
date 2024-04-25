package com.flight.bff.config;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class RestInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientService authorizedClientService;


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        OidcUser principal = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String registrationId = principal.getAuthorizedParty();
        String name = principal.getName();
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(registrationId, name);
        String tokenValue = client.getAccessToken().getTokenValue();
        request.getHeaders().add("Authorization", "Bearer " + tokenValue);
        return execution.execute(request, body);
    }
}
