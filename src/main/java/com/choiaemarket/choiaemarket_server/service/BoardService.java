package com.choiaemarket.choiaemarket_server.service;

import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.dto.request.board.PatchBoardRequestDto;
import com.choiaemarket.choiaemarket_server.dto.request.board.PostBoardRequestDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.DeleteBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetFavoriteBoardListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetFavoriteResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetLatestBoardListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetMyBoardListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetSearchBoardListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.GetUserBoardListResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.PatchBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.PostBoardResponseDto;
import com.choiaemarket.choiaemarket_server.dto.response.board.PutFavoriteResopnseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteBoardListResponseDto> getFavoriteBoardList(String email);
    ResponseEntity<? super GetFavoriteResponseDto> getFavorite(Integer boardNumber, String email);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    ResponseEntity<? super GetMyBoardListResponseDto> getMyBoardList(String email);
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);
    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PutFavoriteResopnseDto> putFavorite(Integer boardNumber, String email);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
}
