package com.choiaemarket.choiaemarket_server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "messages")
@Table(name = "messages")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoomEntity chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_email")
    private UserEntity sender;

    private String message;
    private LocalDateTime timestamp;

    public MessageEntity(ChatRoomEntity chatRoom, UserEntity sender, String message, LocalDateTime timestamp) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }
}
