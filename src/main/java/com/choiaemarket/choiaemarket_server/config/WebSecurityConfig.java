package com.choiaemarket.choiaemarket_server.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.choiaemarket.choiaemarket_server.filter.JwtAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(cors -> cors  // cors : 다른 도메인에서 접근 제한
                .configurationSource(corsConfigrationSource())
            )
            .csrf(CsrfConfigurer::disable)  // csrf : 사이트 간 요청 위조 공격 방지
            .httpBasic(HttpBasicConfigurer::disable)    // 베이직 인증(사용자 이름, 비밀번호 이용) 비활성화
            .sessionManagement(sessionManagement -> sessionManagement   // 세션 기반 인증 비활성화
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(request -> request
                .requestMatchers("/", "api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()    // 해당 경로 권한 부여
                .requestMatchers(HttpMethod.GET, "/api/v1/board/**", "/api/v1/user/*").permitAll()
                .requestMatchers("/ws/**").permitAll()  // WebSocket 엔드포인트 예외 처리
                .anyRequest().authenticated()   // 다른 모든 요청에 대한 인증 요구
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new FailedAuthenticationEntryPoint())
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @ Bean
    protected CorsConfigurationSource corsConfigrationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
            
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{ \"code\": \"AF\", \"message\": \"Authorization Failed\" }");
    }
}