package com.choiaemarket.choiaemarket_server.dto.response.chat;

import com.choiaemarket.choiaemarket_server.common.ResponseCode;
import com.choiaemarket.choiaemarket_server.common.ResponseMessage;
import com.choiaemarket.choiaemarket_server.dto.response.ResponseDto;
import com.choiaemarket.choiaemarket_server.entity.MessageEntity;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetMessageResponseDto extends ResponseDto {
    private List<MessageDto> messages;

    private GetMessageResponseDto(List<MessageEntity> messageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.messages = messageEntities.stream()
                .map(MessageDto::new)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetMessageResponseDto> success(List<MessageEntity> messageEntities) {
        GetMessageResponseDto result = new GetMessageResponseDto(messageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // public static ResponseEntity<ResponseDto> noMessages() {
    //     ResponseDto result = new ResponseDto(ResponseCode.NO_MESSAGES, ResponseMessage.NO_MESSAGES);
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    // }

    @Getter
    public static class MessageDto {
        private final Long id;
        private final String chatRoomId;
        private final String senderEmail;
        private final String message;
        private final String timestamp;

        public MessageDto(MessageEntity messageEntity) {
            this.id = messageEntity.getId();
            this.chatRoomId = messageEntity.getChatRoom().getId().toString();
            this.senderEmail = messageEntity.getSender().getEmail();
            this.message = messageEntity.getMessage();
            this.timestamp = messageEntity.getTimestamp().toString();
        }
    }
}
