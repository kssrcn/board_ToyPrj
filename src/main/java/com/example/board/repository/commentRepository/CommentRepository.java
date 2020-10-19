package com.example.board.repository.commentRepository;

import com.example.board.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);

    Optional<Comment> findOne(Long commentId);

    List<Comment> findAll();

    void delete(Long commentId);
}
