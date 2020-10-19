package com.example.board.api;

import com.example.board.api.dtos.commentDto.CommentRegisterDto;
import com.example.board.api.dtos.commentDto.CommentUpdateDto;
import com.example.board.domain.Comment;
import com.example.board.service.boardService.BoardService;
import com.example.board.service.commentService.CommentService;
import com.example.board.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final UserService userService;
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/comment")
    public void register(@RequestBody CommentRegisterDto commentRegisterDto) {
        Comment comment = Comment.createComment(boardService.findOne(commentRegisterDto.getBoardId()),
                userService.findOne(commentRegisterDto.getUserId()), commentRegisterDto.getCommentContent());
        commentService.register(comment);
    }

    @PutMapping("/comment/{id}")
    public void updateComment(@PathVariable("id") Long commentId, @RequestBody CommentUpdateDto commentUpdateDto) {
        commentService.update(commentId, commentUpdateDto.getCommentContent());
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") Long commentId) {
        commentService.delete(commentId);
    }

}
