package com.choiaemarket.choiaemarket_server.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.ChatRoomRepository;
import com.choiaemarket.choiaemarket_server.service.ChatRoomService;

@Service
public class ChatRoomServiceImplement implements ChatRoomService {
    
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoomEntity findOrCreateChatRoom(UserEntity user1, UserEntity user2) {
        Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findByUser1EmailAndUser2Email(user1.getEmail(), user2.getEmail());
        return chatRoom.orElseGet(() -> {
            ChatRoomEntity newRoom = new ChatRoomEntity(user1, user2);
            chatRoomRepository.save(newRoom);
            return newRoom;
        });
    }
}
