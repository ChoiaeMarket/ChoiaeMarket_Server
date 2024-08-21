package com.choiaemarket.choiaemarket_server.dto.response.board;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.entity.BoardEntity;
import com.choiaemarket.choiaemarket_server.entity.ImageEntity;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetBoardResponseDto extends ResponseDto {
    private int boardNumber;
	private String idol;
	private String type;
	private String name;
	private List<String> boardImageList;
	private String title;
	private String content;
	private int price;
    private int chatCount;
    private int favoriteCount;
	private boolean sold;
	private String writeDatetime;
    private String writerEmail;
	private String writerNickname;
	private String writerProfileImage;

    private GetBoardResponseDto(BoardEntity boardEntity, List<ImageEntity> imageEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for(ImageEntity imageEntity: imageEntities) {
            String boardImage =  imageEntity.getImage();
            boardImageList.add(boardImage);
        }

        this.boardNumber = boardEntity.getBoardNumber();
        this.idol = boardEntity.getIdol();
        this.type = boardEntity.getType();
        this.name = boardEntity.getName();
        this.boardImageList = boardImageList;
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.price = boardEntity.getPrice();
        this.chatCount = boardEntity.getChatCount();
        this.favoriteCount = boardEntity.getFavoriteCount();
        this.sold = boardEntity.getSold();
        this.writeDatetime = boardEntity.getWriteDatetime();
        this.writerEmail = boardEntity.getWriterEmail();
        this.writerNickname = boardEntity.getWriterNickname();
        this.writerProfileImage = boardEntity.getWriterProfileImage();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardEntity boardEntity, List<ImageEntity> imageEntities){
        GetBoardResponseDto result = new GetBoardResponseDto(boardEntity, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
