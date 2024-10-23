package com.choiaemarket.choiaemarket_server.entity;

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

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp createdAt;

    public ChatRoomEntity(UserEntity user1, UserEntity user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
    }
}