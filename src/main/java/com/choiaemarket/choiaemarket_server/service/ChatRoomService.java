package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.chat.PostChatRoomRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.PostChatRoomResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.GetChatRoomListResponseDto;

public interface ChatRoomService {
    ResponseEntity<? super PostChatRoomResponseDto> createChatRoom(PostChatRoomRequestDto requestBody, String user1Email);
    ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(String userEmail); // 채팅방 리스트 가져오기
}
