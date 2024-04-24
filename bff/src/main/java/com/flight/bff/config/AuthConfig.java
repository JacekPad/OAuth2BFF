package com.flight.bff.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@ConfigurationProperties(prefix = "bff.auth")
@Setter @Getter
public class AuthConfig {

    private String postLogoutRedirect;
    private String loginPage;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private RedirectLoginConfig redirectLoginConfig;

    private LogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler clientInitiatedLogoutSuccessHandler
                = new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        clientInitiatedLogoutSuccessHandler.setPostLogoutRedirectUri(postLogoutRedirect);
        return clientInitiatedLogoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2Login(oauth -> {
                    oauth.loginPage(loginPage);
                    oauth.successHandler(redirectLoginConfig);
                })
                .csrf(c -> c.disable())
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new Http403ForbiddenEntryPoint()))
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/public").permitAll();
                    request.anyRequest().authenticated();
                })
                .logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler()));
        return http.build();
    }


}
