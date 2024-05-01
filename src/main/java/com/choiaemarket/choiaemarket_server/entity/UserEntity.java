package com.choiaemarket.choiaemarket_server.entity;

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
}
