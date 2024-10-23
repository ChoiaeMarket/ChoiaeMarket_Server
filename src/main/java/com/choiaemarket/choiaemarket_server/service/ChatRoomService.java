package com.choiaemarket.choiaemarket_server.service;

import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;

public interface ChatRoomService {
    ChatRoomEntity findOrCreateChatRoom(UserEntity user1, UserEntity user2);
}
