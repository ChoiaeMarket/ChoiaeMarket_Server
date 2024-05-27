package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.board.PostBoardRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetFavoriteResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.PostBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.PutFavoriteResopnseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super GetFavoriteResponseDto> getFavorite(Integer boardNumber, String email);
    ResponseEntity<? super PutFavoriteResopnseDto> putFavorite(Integer boardNumber, String email);
}
