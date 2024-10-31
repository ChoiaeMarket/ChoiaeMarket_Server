package com.choiaemarket.choiaemarket_server.service.implement;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.entity.CustomOAuth2User;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService{
    
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        // try {
        //     System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        // } catch (Exception exception) {
        //     exception.printStackTrace();
        // }

        UserEntity userEntity = null;
        String email = null;
        String name = null;
        String nickname = null;
        String tel = null;
        String gender = null;

        if (oauthClientName.equals("kakao")) {
            Map<String, Object> responseMap = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) responseMap.get("profile");

            String fullEmail = (String) responseMap.get("email");
            email = "kakao_" + fullEmail.split("@")[0];
            name = (String) responseMap.get("name");
            nickname = (String) profile.get("nickname");
            tel = ((String) responseMap.get("phone_number"))
            .replace("+82 ", "0")
            .replace("-", "");
            gender = "male".equals(responseMap.get("gender")) ? "0" : "1"; // 0 : 남자, 1 : 여자

            // 닉네임 중복 체크 및 고유 닉네임 생성
            String originalNickname = nickname;
            int count = 1;
            while (userRepository.existsByNickname(nickname)) {
                nickname = originalNickname + count;
                count++;
            }

            userEntity = new UserEntity(email, "kakao", name, nickname, tel, gender);
        }

        if (oauthClientName.equals("naver")) {
            Map<String, Object> responseMap = (Map<String, Object>) oAuth2User.getAttributes().get("response");

            String fullEmail = (String) responseMap.get("email");
            email = "naver_" + fullEmail.split("@")[0];
            name = (String) responseMap.get("name");
            nickname = (String) responseMap.get("nickname");
            tel = ((String) responseMap.get("mobile")).replace("-", "");
            gender = "M".equals(responseMap.get("gender")) ? "0" : "1"; // 0 : 남자, 1 : 여자

            // 닉네임 중복 체크 및 고유 닉네임 생성
            String originalNickname = nickname;
            int count = 1;
            while (userRepository.existsByNickname(nickname)) {
                nickname = originalNickname + count;
                count++;
            }

            userEntity = new UserEntity(email, "naver", name, nickname, tel, gender);
        }

        userRepository.save(userEntity);

        return new CustomOAuth2User(email);

    }

}
