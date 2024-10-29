package com.choiaemarket.choiaemarket_server.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.common.CertificationNumber;
import com.choiaemarket.choiaemarket_server.dto.request.auth.EmailCertificationRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignInRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.auth.SignUpRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.EmailCertificationResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignInResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.auth.SignUpResponseDto;
import com.choiaemarket.choiaemarket_server.entity.CertificationEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.provider.EmailProvider;
import com.choiaemarket.choiaemarket_server.provider.JwtProvider;
import com.choiaemarket.choiaemarket_server.repository.CertificationRepository;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.choiaemarket.choiaemarket_server.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;
    
    private PasswordEncoder passwordEncode = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            
            String email = dto.getEmail();

            // 인증 번호 생성
            String certificationNumber = CertificationNumber.getCertificationNumber();

            // 인증 메일 전송
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            // 전송 결과 저장
            CertificationEntity certificationEntity = new CertificationEntity(email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();

    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            // 제약 조건 검사 
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return SignUpResponseDto.duplicateNickname();

            String tel = dto.getTel();
            boolean existedTel = userRepository.existsByTel(tel);
            if (existedTel) return SignUpResponseDto.duplicateTelNumber();
            
            // password 암호화
            String password = dto.getPassword();
            String encodedPassword = passwordEncode.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        String token = null;

        try {

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return SignInResponseDto.signInFailed();
            
            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncode.matches(password, encodedPassword); // raw password와 암호화된 password 비교
            if (!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    
    }
    
}
