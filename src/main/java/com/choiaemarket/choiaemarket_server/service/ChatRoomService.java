package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.chat.ChatRoomRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.ChatRoomResponseDto;

public interface ChatRoomService {
    ResponseEntity<? super ChatRoomResponseDto> createChatRoom(ChatRoomRequestDto requestBody, String user1Email);
}
