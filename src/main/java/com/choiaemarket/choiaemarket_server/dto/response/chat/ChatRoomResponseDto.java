package com.choiaemarket.choiaemarket_server.dto.response.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class ChatRoomResponseDto extends ResponseDto {
    
    private Long chatRoomId;

    private ChatRoomResponseDto(Long chatRoomId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRoomId = chatRoomId;
    }

    public static ResponseEntity<ChatRoomResponseDto> success(Long chatRoomId) {
        ChatRoomResponseDto result = new ChatRoomResponseDto(chatRoomId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto result = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
        body(result);
    }
}
