package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.auth.CheckCertificationRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.EmailCertificationRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignInRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignUpRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.CheckCertificationResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.EmailCertificationResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.EmailCheckResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignInResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super EmailCheckResponseDto> emailCheck(String email);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}
