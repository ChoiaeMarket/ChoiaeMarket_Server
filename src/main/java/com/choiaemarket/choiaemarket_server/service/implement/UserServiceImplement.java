package com.choiaemarket.choiaemarket_server.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.dto.request.user.PatchProfileRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.user.PatchProfileImageRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.GetSignInUserResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.GetUserResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.PatchProfileResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.user.PatchProfileImageResponseDto;
import com.choiaemarket.choiaemarket_server.entity.BoardEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.BoardRepository;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.choiaemarket.choiaemarket_server.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    
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
    public ResponseEntity<? super PatchProfileResponseDto> patchProfile(PatchProfileRequestDto dto, String email) {

        try {
            
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchProfileResponseDto.notExistUser();

            String nickname = dto.getNickname();
            String tel = dto.getTel();
            
            // 닉네임 중복 체크 (현재 유저의 닉네임과 다를 경우에만)
            if (!userEntity.getNickname().equals(nickname)) {
                boolean existedNickname = userRepository.existsByNickname(nickname);
                if (existedNickname) return PatchProfileResponseDto.duplicateNickname();
            }

            // 닉네임 변경
            userEntity.setNickname(nickname);
            userEntity.setTel(tel);
            userRepository.save(userEntity);

            // 해당 유저가 작성한 게시물의 작성자 닉네임 업데이트
            List<BoardEntity> boards = boardRepository.findByWriterEmail(email);
            for (BoardEntity board : boards) {
                board.setWriterNickname(nickname); // 작성자 닉네임 업데이트
            }
            boardRepository.saveAll(boards); // 모든 변경사항 저장

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchProfileResponseDto.success();

    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {

        try {
            
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchProfileResponseDto.notExistUser();

            String profileImage = dto.getProfileImage();

            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);

            // board 테이블에서 해당 유저의 writer_profile_image 업데이트
            List<BoardEntity> boards = boardRepository.findByWriterEmail(email);
            for (BoardEntity board : boards) {
                board.setWriterProfileImage(profileImage); // 프로필 이미지 업데이트
            }
            boardRepository.saveAll(boards); // 모든 변경사항 저장

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
