package com.choiaemarket.choiaemarket_server.dto.request.board;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {
    
    @NotBlank
    private String idol;
    @NotBlank
    private String type;
    @NotBlank
    private String name;
    @NotNull
    private List<String> boardImageList;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    @Positive
    private int price;
}
