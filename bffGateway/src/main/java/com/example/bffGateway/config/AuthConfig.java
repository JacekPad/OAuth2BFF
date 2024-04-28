package com.example.bffGateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestHandler;
import org.springframework.security.web.server.csrf.XorServerCsrfTokenRequestAttributeHandler;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;
@Configuration
@ConfigurationProperties(prefix = "bff.auth")
@Setter @Getter
@EnableWebFluxSecurity
public class AuthConfig {

    private String postLogoutRedirect;
    @Autowired
    private ReactiveClientRegistrationRepository clientRegistrationRepository;



    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri(postLogoutRedirect);
        return oidcLogoutSuccessHandler;
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers("api/public/**","/info","/angular/**").permitAll()
                        .anyExchange().authenticated()
                )
                .exceptionHandling(x -> x.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.FORBIDDEN)))
                .oauth2Login(c -> c.authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("http://localhost:9090/angular/")))
//                csrf not working with keycloak logout?
                .csrf(c -> c.disable())
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(requestHandler())
//                )
                .logout((logout) -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                );
        return http.build();
    }

//    private ServerCsrfTokenRequestHandler requestHandler() {
//        XorServerCsrfTokenRequestAttributeHandler delegate = new XorServerCsrfTokenRequestAttributeHandler();
//        return delegate::handle;
//    }

//    @Bean
//    WebFilter csrfCookieWebFilter() {
//        return (exchange, chain) -> {
//            Mono<CsrfToken> csrfToken = exchange.getAttributeOrDefault(CsrfToken.class.getName(), Mono.empty());
//            return csrfToken.doOnSuccess(token -> {
//                /* Ensures the token is subscribed to. */
//            }).then(chain.filter(exchange));
//        };
//    }

}
