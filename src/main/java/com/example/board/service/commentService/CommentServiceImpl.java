package com.example.board.service.commentService;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.repository.commentRepository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Override
    @Transactional
    public Long register(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    @Transactional
    public void update(Long commentId, String content) {
        Optional<Comment> findedComment = commentRepository.findOne(commentId);
        findedComment.ifPresent(comment -> comment.changeContent(content));
    }

    @Override
    @Transactional
    public void delete(Long commentId) {
        commentRepository.delete(commentId);
    }

    @Override
    public Comment findOne(Long commentId) {
        Optional<Comment> findedComment = commentRepository.findOne(commentId);
        return findedComment.orElseThrow(() -> new NoResultException("댓글이 존재하지 않습니다."));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
