package com.choiaemarket.choiaemarket_server.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatPk implements Serializable {
    @Column(name="chat_number")
    private int chatNumber;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="board_number")
    private int boardNumber;
}

