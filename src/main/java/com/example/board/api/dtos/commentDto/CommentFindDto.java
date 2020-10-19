package com.example.board.api.dtos.commentDto;


import com.example.board.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentFindDto {
    private Long commentId;
    private String commentContent;
    private LocalDateTime commentDate;

    public CommentFindDto(Comment comment) {
        this.commentId = comment.getId();
        this.commentContent = comment.getContent();
        this.commentDate = comment.getCommentDate();
    }
}
