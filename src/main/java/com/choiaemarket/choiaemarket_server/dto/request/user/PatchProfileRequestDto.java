package com.choiaemarket.choiaemarket_server.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchProfileRequestDto {
    
    @NotBlank
    private String nickname;

    @NotBlank
    private String tel;
}
