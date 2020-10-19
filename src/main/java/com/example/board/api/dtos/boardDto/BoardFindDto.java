package com.example.board.api.dtos.boardDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardFindDto {
    private Long boardId;
    private String boardTitle;
    private LocalDateTime boardDate;
    private String userNickName;
}
