package com.choiaemarket.choiaemarket_server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;
import com.choiaemarket.choiaemarket_server.entity.UserEntity;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    Optional<ChatRoomEntity> findByUser1AndUser2(UserEntity user1, UserEntity user2);

    List<ChatRoomEntity> findByUser1EmailOrUser2Email(String user1Email, String user2Email);
}
