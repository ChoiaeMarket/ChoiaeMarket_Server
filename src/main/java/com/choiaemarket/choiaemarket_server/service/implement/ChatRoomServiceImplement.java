package com.choiaemarket.choiaemarket_server.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.dto.request.chat.ChatRoomRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.ChatRoomResponseDto;
import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;
import com.choiaemarket.choiaemarket_server.repository.ChatRoomRepository;
import com.choiaemarket.choiaemarket_server.repository.UserRepository;
import com.choiaemarket.choiaemarket_server.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImplement implements ChatRoomService {
    
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super ChatRoomResponseDto> createChatRoom(ChatRoomRequestDto requestBody, String user1Email) {
        try {
            UserEntity user1 = userRepository.findByEmail(user1Email);
            UserEntity user2 = userRepository.findByEmail(requestBody.getUser2Email());

            if (user1 == null || user2 == null) {
                return ChatRoomResponseDto.noExistUser();
            }

            // 채팅방이 이미 존재하는지 확인 후 없으면 새로 생성
            ChatRoomEntity chatRoom = chatRoomRepository.findByUser1AndUser2(user1, user2)
                .orElseGet(() -> {
                    ChatRoomEntity newChatRoom = new ChatRoomEntity(user1, user2);
                    chatRoomRepository.save(newChatRoom);
                    return newChatRoom;
                });

            return ChatRoomResponseDto.success(chatRoom.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return ChatRoomResponseDto.databaseError();
        }
    }
}
