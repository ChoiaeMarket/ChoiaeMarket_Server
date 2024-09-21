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
public class GetSearchBoardListResponseDto extends ResponseDto{
    
    private List<BoardListItem> searchList;
    
    private GetSearchBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetSearchBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
