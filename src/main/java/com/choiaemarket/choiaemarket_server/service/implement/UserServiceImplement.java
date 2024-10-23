package com.choiaemarket.choiaemarket_server.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.dto.request.user.PatchNicknameRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.user.PatchProfileImageRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.GetSignInUserResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.GetUserResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.PatchNicknameResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.PatchProfileImageResponseDto;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.choiaemarket.choiaemarket_server.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
        
        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email); // 로그인한 유저를 email로 판단
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser(); // 없으면 notExistUser 반환
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {
        
        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetUserResponseDto.notExistUser();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(userEntity);

    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {

        try {
            
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchNicknameResponseDto.notExistUser();

            String nickname = dto.getNickname();
            
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchNicknameResponseDto.success();

    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {

        try {
            
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchNicknameResponseDto.notExistUser();

            String profileImage = dto.getProfileImage();

            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchProfileImageResponseDto.success();

    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
