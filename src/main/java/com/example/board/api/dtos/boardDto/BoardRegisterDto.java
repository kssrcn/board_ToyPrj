package com.example.board.api.dtos.boardDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterDto {
    private String boardTitle;
    private Long userId;
    private String boardContent;
}
