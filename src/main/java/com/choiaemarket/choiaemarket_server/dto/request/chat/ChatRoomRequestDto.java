package com.choiaemarket.choiaemarket_server.dto.request.chat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomRequestDto {
    
    @NotBlank
    @Email
    private String user2Email;  // 채팅 상대방의 이메일 (user1은 인증된 사용자의 이메일)
    
}
