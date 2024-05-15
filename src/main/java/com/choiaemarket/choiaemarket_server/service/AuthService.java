package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.auth.SignInRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignUpRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignInResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
