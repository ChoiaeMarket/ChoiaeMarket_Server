package com.choiaemarket.choiaemarket_server.entity;

import com.choiaemarket.choiaemarket_server.dto.request.board.PostBoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;
    private int productNumber;
    
    public BoardEntity(PostBoardRequestDto dto, String email, String nickname, String profileImage, int productNumber){

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.idol = dto.getIdol();
        this.type = dto.getType();
        this.name = dto.getName();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.price = dto.getPrice();
        this.chatCount = 0;
        this.favoriteCount = 0;
        this.sold = false;
        this.writeDatetime = writeDatetime;
        this.writerEmail = email;
        this.writerNickname = nickname;
        this.writerProfileImage = profileImage;
        this.productNumber = productNumber;
    }

}
