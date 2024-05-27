package com.choiaemarket.choiaemarket_server.dto.request.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    
    @NotBlank @Email // 이메일 형식
    private String email;

    @NotBlank @Size(min=8, max=20)  // 크기 제한
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String nickname;

    @NotBlank @Pattern(regexp="^[0-9]{11,13}$") // 정규 표현식
    private String tel;

    @NotBlank
    private String gender;

    @NotNull @AssertTrue
    private Boolean agreedPersonal;

    @NotBlank
    private String profileImage;
}
