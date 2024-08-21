package com.choiaemarket.choiaemarket_server.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetFavoriteResponseDto extends ResponseDto {
    private boolean isFavorite;

    private GetFavoriteResponseDto(boolean isFavorite) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.isFavorite = isFavorite;
    }

    public static ResponseEntity<GetFavoriteResponseDto> success(boolean isFavorite) {
        GetFavoriteResponseDto result = new GetFavoriteResponseDto(isFavorite);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}