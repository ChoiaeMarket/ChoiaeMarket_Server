package com.choiaemarket.choiaemarket_server.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
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

    // 기본 생성자
    public ChatRoomEntity() {}

    // 필요한 생성자
    public ChatRoomEntity(UserEntity user1, UserEntity user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public UserEntity getUser1() {
        return user1;
    }

    public UserEntity getUser2() {
        return user2;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser1(UserEntity user1) {
        this.user1 = user1;
    }

    public void setUser2(UserEntity user2) {
        this.user2 = user2;
    }
}
