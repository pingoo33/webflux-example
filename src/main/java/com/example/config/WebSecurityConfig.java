package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@EnableWebFluxSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        http
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()

                .authorizeExchange()
                .pathMatchers(
                        "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**",
                        "/webjars/**", "/swagger/**", "/swagger-ui.html", "/swagger-ui/**",

                        "/api/*/auth/sign-in", "/api/*/auth/sign-up"
                )
                .permitAll()
                .anyExchange().authenticated();

        return http.build();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
