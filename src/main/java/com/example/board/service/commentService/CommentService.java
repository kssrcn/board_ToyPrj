package com.example.board.service.commentService;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Long register(Comment comment);

    void update(Long commentId, String content);

    void delete(Long commentId);

    Comment findOne(Long commentId);

    List<Comment> findAll();
}
