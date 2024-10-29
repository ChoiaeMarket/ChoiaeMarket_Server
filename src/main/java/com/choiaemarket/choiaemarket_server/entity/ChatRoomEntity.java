package com.choiaemarket.choiaemarket_server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "chat_rooms")
@Table(name = "chat_rooms")
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_email")
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "user2_email")
    private UserEntity user2;

    @Column(name = "last_message", columnDefinition = "TEXT")
    private String lastMessage; // 마지막 메시지 내용

    @Column(name = "last_timestamp")
    private LocalDateTime lastTimestamp; // 마지막 메시지 전송 시간

    public ChatRoomEntity(UserEntity user1, UserEntity user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.lastMessage = null; // 초기값 null
        this.lastTimestamp = null; // 초기값 null
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setLastTimestamp(LocalDateTime lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }
}