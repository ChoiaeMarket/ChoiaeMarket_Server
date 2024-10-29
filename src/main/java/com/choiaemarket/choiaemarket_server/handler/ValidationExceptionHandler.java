package com.choiaemarket.choiaemarket_server.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;

@RestControllerAdvice
public class ValidationExceptionHandler {
    
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseDto> validationExceptionHandler(Exception excetion) {
        return ResponseDto.validationFalied();
    }

}
