package com.choiaemarket.choiaemarket_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choiaemarket.choiaemarket_server.entity.ChatEntity;
import com.choiaemarket.choiaemarket_server.entity.primaryKey.ChatPk;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, ChatPk>{
    
}
