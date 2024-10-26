package com.choiaemarket.choiaemarket_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choiaemarket.choiaemarket_server.dto.chat.ChatMessage;
import com.choiaemarket.choiaemarket_server.dto.request.chat.PostChatRoomRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.GetChatRoomListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.GetMessageResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.PostChatRoomResponseDto;
import com.choiaemarket.choiaemarket_server.service.ChatRoomService;
import com.choiaemarket.choiaemarket_server.service.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    // 새로운 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity<? super PostChatRoomResponseDto> createChatRoom(
        @RequestBody @Validated PostChatRoomRequestDto requestBody,
        @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostChatRoomResponseDto> response = chatRoomService.createChatRoom(requestBody, email);
        return response;
    }

    // 메시지 가져오기
    @GetMapping("/room/{roomId}/messages")
    public ResponseEntity<? super GetMessageResponseDto> getMessages(@PathVariable("roomId") Long roomId) {
        ResponseEntity<? super GetMessageResponseDto> response = messageService.getMessages(roomId);
        return response;
    }

    // 채팅방 리스트 가져오기
    @GetMapping("/room-list/{email}")
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(@PathVariable("email") String email) {
        ResponseEntity<? super GetChatRoomListResponseDto> response = chatRoomService.getChatRoomList(email);
        return response;
    }

    // WebSocket 메시지 전송
    @MessageMapping("/chat.sendMessage/{roomId}") // 클라이언트에서 메시지를 보낼 경로
    @SendTo("/topic/{roomId}") // 구독된 사용자에게 메시지를 전송할 경로
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        messageService.saveMessage(chatMessage); // 메시지 저장
        return chatMessage;
    }
}
