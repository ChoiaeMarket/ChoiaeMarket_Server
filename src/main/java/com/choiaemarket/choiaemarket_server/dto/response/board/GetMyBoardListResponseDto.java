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
public class GetMyBoardListResponseDto extends ResponseDto  {
    
    private List<BoardListItem> myList;

    private GetMyBoardListResponseDto(List<BoardListViewEntity> boradListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.myList = BoardListItem.getList(boradListViewEntities);
    }
    
        public static ResponseEntity<GetMyBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetMyBoardListResponseDto result = new GetMyBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
