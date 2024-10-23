package com.choiaemarket.choiaemarket_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    Optional<ChatRoomEntity> findByUser1EmailAndUser2Email(String user1Email, String user2Email);
}
