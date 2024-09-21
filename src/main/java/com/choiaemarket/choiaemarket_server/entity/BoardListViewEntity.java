package com.choiaemarket.choiaemarket_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board_list_view")
@Table(name="board_list_view")
public class BoardListViewEntity {
    
    @Id
    private int boardNumber;
    private String idol;
    // private String type;
    private String name;
    private String image;
    private String title;
    private String content;
    private int price;
    private int chatCount;
    private int favoriteCount;
    private boolean sold;
    private String writeDatetime;
    // private String writerProfileImage;
    private String writerEmail;
    
}
