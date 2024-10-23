package com.choiaemarket.choiaemarket_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choiaemarket.choiaemarket_server.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
    
}
