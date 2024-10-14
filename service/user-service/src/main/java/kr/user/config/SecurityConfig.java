package kr.user.config;

import kr.nyamnyam.service.UserService;
import kr.nyamnyam.service.impl.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenServiceImpl tokenService; // JwtTokenProvider 대신 TokenServiceImpl 주입
    private final UserService userService;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenService, userService); // JwtTokenProvider 대신 TokenServiceImpl 사용

        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS).permitAll() // OPTIONS 요청 인증 제외
                        .anyExchange().permitAll() // 다른 모든 요청도 인증 없이 허용
                )
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION); // JWT 필터 등록

        return http.build();
    }
}
