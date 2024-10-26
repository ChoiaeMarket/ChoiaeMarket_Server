package com.choiaemarket.choiaemarket_server.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.dto.chat.ChatMessage;
import com.choiaemarket.choiaemarket_server.dto.response.chat.GetMessageResponseDto;
import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;
import com.choiaemarket.choiaemarket_server.entity.MessageEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.ChatRoomRepository;
import com.choiaemarket.choiaemarket_server.repository.MessageRepository;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.choiaemarket.choiaemarket_server.service.MessageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImplement implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public void saveMessage(ChatMessage chatMessage) {
        ChatRoomEntity chatRoom = chatRoomRepository.findById(Long.valueOf(chatMessage.getRoomId())).orElse(null);
        UserEntity sender = userRepository.findByEmail(chatMessage.getSenderEmail());

        if (chatRoom != null && sender != null) {
            MessageEntity message = new MessageEntity(chatRoom, sender, chatMessage.getContent(), LocalDateTime.now());
            messageRepository.save(message);
        }
    }

    @Override
    public ResponseEntity<? super GetMessageResponseDto> getMessages(Long roomId) {
        List<MessageEntity> messages = messageRepository.findByChatRoomId(roomId); // 채팅방 ID로 메시지 조회

        if (messages.isEmpty()) {
            return GetMessageResponseDto.databaseError();
        }

        return GetMessageResponseDto.success(messages);
    }

}
