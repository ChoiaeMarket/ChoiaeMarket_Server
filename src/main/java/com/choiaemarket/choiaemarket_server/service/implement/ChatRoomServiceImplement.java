package com.choiaemarket.choiaemarket_server.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.choiaemarket.choiaemarket_server.dto.request.chat.PostChatRoomRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.GetChatRoomListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.chat.PostChatRoomResponseDto;
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
    public ResponseEntity<? super PostChatRoomResponseDto> createChatRoom(PostChatRoomRequestDto requestBody, String user1Email) {
        try {
            UserEntity user1 = userRepository.findByEmail(user1Email);
            UserEntity user2 = userRepository.findByEmail(requestBody.getUser2Email());

            if (user1 == null || user2 == null) {
                return PostChatRoomResponseDto.noExistUser();
            }

            // 채팅방이 이미 존재하는지 확인 후 없으면 새로 생성
            ChatRoomEntity chatRoom = chatRoomRepository.findByUser1AndUser2(user1, user2)
                .or(() -> chatRoomRepository.findByUser1AndUser2(user2, user1)) // 순서 바꿔서도 확인
                .orElseGet(() -> {
                    ChatRoomEntity newChatRoom = new ChatRoomEntity(user1, user2);
                    chatRoomRepository.save(newChatRoom);
                    return newChatRoom;
                });

            return PostChatRoomResponseDto.success(chatRoom.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return PostChatRoomResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(String userEmail) {
        List<ChatRoomEntity> chatRooms = chatRoomRepository.findByUser1EmailOrUser2Email(userEmail, userEmail);
        return GetChatRoomListResponseDto.success(chatRooms);
    }
    
}
