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

        if (oauthClientName.equals("kakao")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("kakao_account");
            email = responseMap.get("email");
            name = responseMap.get("name");
            userEntity = new UserEntity(email, "kakao", name);
        }

        if (oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            email = responseMap.get("email");
            name = responseMap.get("name");
            userEntity = new UserEntity(email, "naver", name);
        }

        userRepository.save(userEntity);

        return new CustomOAuth2User(email);

    }

}
