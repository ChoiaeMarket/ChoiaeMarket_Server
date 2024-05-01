package com.choiaemarket.choiaemarket_server.entity;

import com.choiaemarket.choiaemarket_server.entity.primaryKey.ChatPk;
import com.choiaemarket.choiaemarket_server.entity.primaryKey.FavoritePk;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="chat")
@Table(name="chat")
@IdClass(ChatPk.class)
public class ChatEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int chatNumber;
    private String chatDatetime;
    private String content;
    private String userEmail;
    private int boardNumber;
}
