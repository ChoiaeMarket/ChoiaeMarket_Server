package com.choiaemarket.choiaemarket_server.dto.response.chat;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.entity.ChatRoomEntity;

import lombok.Getter;

@Getter
public class GetChatRoomListResponseDto extends ResponseDto {
    private List<ChatRoomEntity> chatRoomList; // 채팅방 리스트

    private GetChatRoomListResponseDto(List<ChatRoomEntity> chatRoomList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRoomList = chatRoomList;
    }

    public static ResponseEntity<GetChatRoomListResponseDto> success(List<ChatRoomEntity> chatRoomList) {
        GetChatRoomListResponseDto result = new GetChatRoomListResponseDto(chatRoomList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
