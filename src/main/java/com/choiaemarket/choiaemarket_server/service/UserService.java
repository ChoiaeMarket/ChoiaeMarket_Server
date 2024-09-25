package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.user.PatchNicknameRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.user.PatchProfileImageRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.GetSignInUserResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.GetUserResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.PatchNicknameResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.PatchProfileImageResponseDto;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);   // 로그인한 유저를 email로 판단
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);

}
