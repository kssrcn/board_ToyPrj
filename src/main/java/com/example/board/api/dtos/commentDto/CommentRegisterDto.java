package com.example.board.api.dtos.commentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRegisterDto {
    private Long userId;
    private Long boardId;
    private String commentContent;
}
