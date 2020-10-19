package com.example.board.service.commentService;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.domain.User;
import com.example.board.repository.commentRepository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.NoResultException;
import java.util.Optional;

import static org.junit.Assert.*;

public class CommentServiceImplTest {

    User user;
    Board board;
    Comment comment;

    @Before
    public void setUp() {
        user = User.createUser("leaf", "김남현");
        board = Board.createBoard(user, "JPA 책 사세요", "재밌는 이야기!");
        comment = Comment.createComment(board, user, "이거 재밌어요");
    }

    @Test
    public void 댓글수정() {
        CommentRepository mockCommentRepository = Mockito.mock(CommentRepository.class);
        Mockito.when(mockCommentRepository.findOne(Mockito.anyLong())).thenReturn(Optional.of(comment));
        CommentService commentService = new CommentServiceImpl(mockCommentRepository);
        commentService.update(comment.getId(), "좋은 책입니다");
        assertEquals("좋은 책입니다",comment.getContent());
    }

    @Test(expected = NoResultException.class)
    public void 존재하지_않는_댓글_조회() {
        CommentRepository mockCommentRepository = Mockito.mock(CommentRepository.class);
        Mockito.when(mockCommentRepository.findOne(Mockito.anyLong())).thenReturn(Optional.empty());
        CommentService commentService = new CommentServiceImpl(mockCommentRepository);
        commentService.findOne(comment.getId());

        fail("댓글이 존재하지 않아야 합니다.");
    }


}