package com.choiaemarket.choiaemarket_server.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class EmailCheckResponseDto extends ResponseDto{
    
    private EmailCheckResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<EmailCheckResponseDto> success() {
        EmailCheckResponseDto result = new EmailCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateEmail(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
