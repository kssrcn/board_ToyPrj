package com.example.board.service.boardService;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.domain.User;
import com.example.board.repository.boardRepository.BoardRepository;
import com.example.board.repository.commentRepository.CommentRepository;
import com.example.board.service.commentService.CommentService;
import com.example.board.service.commentService.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.NoResultException;
import java.util.Optional;

import static org.junit.Assert.*;

public class BoardServiceImplTest {

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
    public void 글수정() {
        User user = User.createUser("leaf", "김남현");
        Board board = Board.createBoard(user, "JPA 책 사세요", "재밌는 이야기!");
        BoardRepository mockBoardRepository = Mockito.mock(BoardRepository.class);
        Mockito.when(mockBoardRepository.findOne(Mockito.anyLong())).thenReturn(Optional.of(board));
        BoardService boardService = new BoardServiceImpl(mockBoardRepository);
        boardService.update(board.getId(), "더 재밌는 이야기!");
        assertEquals("더 재밌는 이야기!", board.getContent());
    }

    @Test(expected = NoResultException.class)
    public void 존재하지_않는_글_조회() {
        BoardRepository mockBoardRepository = Mockito.mock(BoardRepository.class);
        Mockito.when(mockBoardRepository.findOne(Mockito.anyLong())).thenReturn(Optional.empty());
        BoardService boardService = new BoardServiceImpl(mockBoardRepository);
        boardService.findOne(board.getId());

        fail("글이 존재하지 않아야 합니다.");
    }

}