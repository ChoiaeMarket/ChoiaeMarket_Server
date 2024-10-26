package com.choiaemarket.choiaemarket_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choiaemarket.choiaemarket_server.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
    List<MessageEntity> findByChatRoomId(Long chatRoomId);
}
