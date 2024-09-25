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
    private String name;
    private String nickname;
    private String tel;
    private String gender;
    private String profileImage;
    private boolean agreedPersonal;

    public UserEntity(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.tel = dto.getTel();
        this.gender = dto.getGender();
        this.agreedPersonal = dto.getAgreedPersonal();
        this.profileImage = dto.getProfileImage();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
