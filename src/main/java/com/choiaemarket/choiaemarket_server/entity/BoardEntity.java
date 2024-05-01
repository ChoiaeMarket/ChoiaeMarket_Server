package com.choiaemarket.choiaemarket_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int boardNumber;
    private String idol;
    private String type;
    private String name;
    private String title;
    private String content;
    private int price;
    private int chatCount;
    private int favoriteCount;
    private Boolean sold;
    private String writeDatetime;
    private String writerNickname;
    private String writerProfileImage;
    private String writerEmail;
    private int productNumber;
}
