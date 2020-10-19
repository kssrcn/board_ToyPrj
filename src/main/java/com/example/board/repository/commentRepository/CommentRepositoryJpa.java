package com.example.board.repository.commentRepository;

import com.example.board.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    private final EntityManager em;

    @Override
    public void save(Comment comment) {
        em.persist(comment);
    }

    @Override
    public Optional<Comment> findOne(Long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public void delete(Long commentId) {
        Optional<Comment> findedComment = findOne(commentId);
        findedComment.ifPresent(em::remove);
    }
}
