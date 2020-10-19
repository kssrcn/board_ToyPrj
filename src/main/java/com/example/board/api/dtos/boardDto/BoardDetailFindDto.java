package com.example.board.api.dtos.boardDto;

import com.example.board.api.dtos.commentDto.CommentFindDto;
import com.example.board.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardDetailFindDto extends BoardFindDto {
    private String boardContent;
    private List<CommentFindDto> commentFindDtos;

    public BoardDetailFindDto(Long boardId, String boardTitle, LocalDateTime boardDate,
                              String userNickName, String boardContent, List<Comment> comments) {
        super(boardId, boardTitle, boardDate, userNickName);
        this.boardContent = boardContent;
        this.commentFindDtos = comments.stream()
                .map(CommentFindDto::new)
                .collect(Collectors.toList());
    }
}
