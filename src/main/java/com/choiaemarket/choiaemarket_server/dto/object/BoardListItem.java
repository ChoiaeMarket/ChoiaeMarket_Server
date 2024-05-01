package com.choiaemarket.choiaemarket_server.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
    private int boardNumber;
    private String idol;
    private String type;
    private String name;
    private String image;
    private String title;
    private String content;
    private int price;
    private int chatCount;
    private int favoriteCount;
    private boolean sold;
    private String writeDatetime;
    private String writerNickname;
    private String writerProfileImage;
}
