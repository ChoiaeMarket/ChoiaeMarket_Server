package com.choiaemarket.choiaemarket_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choiaemarket.choiaemarket_server.dto.chat.ChatMessage;
import com.choiaemarket.choiaemarket_server.dto.request.chat.ChatRoomRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.ChatRoomResponseDto;
import com.choiaemarket.choiaemarket_server.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatRoomService chatRoomService;

    // 새로운 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity<? super ChatRoomResponseDto> createChatRoom(
        @RequestBody @Validated ChatRoomRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super ChatRoomResponseDto> response = chatRoomService.createChatRoom(requestBody, email);
        return response;
    }

    // WebSocket 메시지 전송
    @MessageMapping("/chat.sendMessage/{roomId}") // 클라이언트에서 메시지를 보낼 경로
    @SendTo("/topic/{roomId}") // 구독된 사용자에게 메시지를 전송할 경로
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
