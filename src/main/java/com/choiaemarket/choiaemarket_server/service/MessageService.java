package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.message.ChatMessage;
import com.choiaemarket.choiaemarket_server.dto.response.chat.GetMessageResponseDto;

public interface MessageService {
    void saveMessage(ChatMessage chatMessage);
    ResponseEntity<? super GetMessageResponseDto> getMessages(Long roomId);
}
