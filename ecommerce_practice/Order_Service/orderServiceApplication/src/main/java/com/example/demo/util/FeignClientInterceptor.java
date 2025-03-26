package com.example.demo.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignClientInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Jwt jwt = getJwtToken();
                if (jwt != null) {
                    requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
                }
            }
        };
    }

    private Jwt getJwtToken() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return authentication.getToken();
        }
        return null;
    }
}

