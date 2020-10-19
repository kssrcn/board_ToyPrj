package com.example.board.repository.commentRepository;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.domain.User;
import com.example.board.repository.boardRepository.BoardRepository;
import com.example.board.repository.userRepository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentRepositoryJpaTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    Comment comment1;
    Comment comment2;
    Comment comment3;

    @Before
    public void setUp() {
        User user1 = User.createUser("leaf", "김남현");
        User user2 = User.createUser("mmbaby", "이쮼민");
        User user3 = User.createUser("godtmdals", "장경숙씨");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        Board board = Board.createBoard(user1, "JPA 책 사세요", "JPA 이야기");
        boardRepository.save(board);
        comment1 = Comment.createComment(board,user1, "이거 재밌어요1");
        comment2 = Comment.createComment(board,user2, "이거 재밌어요2");
        comment3 = Comment.createComment(board,user3, "이거 재밌어요3");
    }

    @Test
    public void 저장() {
        commentRepository.save(comment1);
        Optional<Comment> findedComment = commentRepository.findOne(comment1.getId());
        checkComment(comment1,findedComment.get());
    }

    @Test
    public void 전체조회() {
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
        List<Comment> findedComments = commentRepository.findAll();
        assertEquals(3, findedComments.size());

        checkComment(comment1,findedComments.get(0));
        checkComment(comment2,findedComments.get(1));
        checkComment(comment3,findedComments.get(2));
    }

    @Test
    public void 글삭제() {
        commentRepository.save(comment1);
        commentRepository.delete(comment1.getId());
        Optional<Comment> findedComment = commentRepository.findOne(comment1.getId());
        assertEquals(Optional.empty(), findedComment);
    }

    private void checkComment(Comment expectedComment, Comment actualComment) {
        assertEquals(expectedComment.getId(), actualComment.getId());
        assertEquals(expectedComment.getBoard(), actualComment.getBoard());
        assertEquals(expectedComment.getUser(), actualComment.getUser());
        assertEquals(expectedComment.getContent(), actualComment.getContent());
    }

}