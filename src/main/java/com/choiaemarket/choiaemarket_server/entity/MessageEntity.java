package com.choiaemarket.choiaemarket_server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
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

    private String content;
    private LocalDateTime timestamp;

    // 기본 생성자
    public MessageEntity() {}

    // 필요한 생성자
    public MessageEntity(ChatRoomEntity chatRoom, UserEntity sender, String content, LocalDateTime timestamp) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public ChatRoomEntity getChatRoom() {
        return chatRoom;
    }

    public UserEntity getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setChatRoom(ChatRoomEntity chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
