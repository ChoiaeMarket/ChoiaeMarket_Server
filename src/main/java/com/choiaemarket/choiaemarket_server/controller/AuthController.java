package com.choiaemarket.choiaemarket_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choiaemarket.choiaemarket_server.dto.request.auth.CheckCertificationRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.EmailCertificationRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignInRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignUpRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.CheckCertificationResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.EmailCertificationResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.EmailCheckResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignInResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignUpResponseDto;
import com.choiaemarket.choiaemarket_server.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// 컨트롤러에는 비즈니스 로직을 적으면 안된다!!
// 입력(request)에 대한 출력(response)의 검증 처리를 하는 구역
// 비즈니스 로직은 서비스에서 진행
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
        @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
        @RequestBody @Valid EmailCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @GetMapping("/email-check/{email}")
        public ResponseEntity<? super EmailCheckResponseDto> emailCheck(
        @PathVariable("email") String email
    ) {
        ResponseEntity<? super EmailCheckResponseDto> response = authService.emailCheck(email);
        return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
