package com.choiaemarket.choiaemarket_server.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {
    
    private String email;
    private String name;
    private String nickname;
    private String tel;
    private String gender;
    private String profileImage;

    private GetSignInUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.nickname = userEntity.getNickname();
        this.tel = userEntity.getTel();
        this.gender = userEntity.getGender();
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity){
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
