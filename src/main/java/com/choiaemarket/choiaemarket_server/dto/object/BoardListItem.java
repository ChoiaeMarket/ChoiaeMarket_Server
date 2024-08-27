package com.choiaemarket.choiaemarket_server.dto.object;

import com.choiaemarket.choiaemarket_server.entity.BoardListViewEntity;

import java.util.List;
import java.util.ArrayList;

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

    public BoardListItem(BoardListViewEntity boardListViewEntity) {
        this.boardNumber = boardListViewEntity.getBoardNumber();
        this.idol = boardListViewEntity.getIdol();
        this.type = boardListViewEntity.getType();
        this.name = boardListViewEntity.getName();
        this.image = boardListViewEntity.getImage();
        this.title = boardListViewEntity.getTitle();
        this.content = boardListViewEntity.getContent();
        this.price = boardListViewEntity.getPrice();
        this.chatCount = boardListViewEntity.getChatCount();
        this.favoriteCount = boardListViewEntity.getFavoriteCount();
        this.sold = boardListViewEntity.isSold();
        this.writeDatetime = boardListViewEntity.getWriteDatetime();
        this.writerNickname = boardListViewEntity.getWriterNickname();
        this.writerProfileImage = boardListViewEntity.getWriterProfileImage();
    }

    public static List<BoardListItem> getList(List<BoardListViewEntity> boardListViewEntities) {
        List<BoardListItem> list = new ArrayList<>();
        for (BoardListViewEntity boardListViewEntity: boardListViewEntities) {
            BoardListItem boardListItem = new BoardListItem(boardListViewEntity);
            list.add(boardListItem);
        }
        return list;
    }
}
