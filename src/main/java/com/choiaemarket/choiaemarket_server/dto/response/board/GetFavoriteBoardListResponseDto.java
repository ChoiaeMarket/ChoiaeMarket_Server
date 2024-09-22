package com.choiaemarket.choiaemarket_server.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.object.BoardListItem;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.entity.BoardListViewEntity;

import lombok.Getter;

@Getter
public class GetFavoriteBoardListResponseDto extends ResponseDto {
    
    private List<BoardListItem> favoriteList;

    private GetFavoriteBoardListResponseDto(List<BoardListViewEntity> boradListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteList = BoardListItem.getList(boradListViewEntities);
    }
    
        public static ResponseEntity<GetFavoriteBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetFavoriteBoardListResponseDto result = new GetFavoriteBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
