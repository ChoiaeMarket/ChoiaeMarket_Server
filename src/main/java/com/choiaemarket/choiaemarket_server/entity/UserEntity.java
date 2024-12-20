package com.choiaemarket.choiaemarket_server.entity;

import com.choiaemarket.choiaemarket_server.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    
    @Id
    private String email;
    private String password;
    private String type;
    private String name;
    private String nickname;
    private String tel;
    private String gender;
    private String profileImage;
    private boolean agreedPersonal;

    public UserEntity(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.type = "app";
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.tel = dto.getTel();
        this.gender = dto.getGender();
        this.agreedPersonal = true;
        this.profileImage = dto.getProfileImage();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public UserEntity (String email, String type, String name, String nickname, String tel, String gender){
        this.email = email;
        this.password = "Password"; // 의미 없음
        this.type = type;
        this.name = name;
        this.nickname = nickname;
        this.tel = tel;
        this.gender = gender;
        this.agreedPersonal = true;
    }
    
}
