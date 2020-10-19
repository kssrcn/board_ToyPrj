package com.example.board.repository.boardRepository;

import com.example.board.domain.Board;
import com.example.board.domain.User;
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
public class BoardRepositoryJpaTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    Board board1;
    Board board2;
    Board board3;

    @Before
    public void setUp() {
        User user1 = User.createUser("leaf", "김남현");
        User user2 = User.createUser("mmbaby", "이쮼민");
        User user3 = User.createUser("godtmdals", "장경숙씨");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        board1 = Board.createBoard(user1, "JPA 책 사세요", "스프링과 JPA 이야기");
        board2 = Board.createBoard(user2, "React 책 사세요", "리액트와 API 호출 이야기");
        board3 = Board.createBoard(user3, "비전공자 도전기", "개발 하고 싶다! 이야기");
    }

    @Test
    public void 저장() {
        boardRepository.save(board1);
        Optional<Board> findedBoard = boardRepository.findOne(board1.getId());
        checkBoard(board1, findedBoard.get());
    }

    @Test
    public void 전체조회() {
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        List<Board> findedBoards = boardRepository.findAll();
        assertEquals(3, findedBoards.size());

        checkBoard(board1, findedBoards.get(0));
        checkBoard(board2, findedBoards.get(1));
        checkBoard(board3, findedBoards.get(2));
    }

    @Test
    public void 글삭제() {
        boardRepository.save(board1);
        boardRepository.delete(board1.getId());
        Optional<Board> findedBoard = boardRepository.findOne(board1.getId());
        assertEquals(Optional.empty(), findedBoard);
    }

    private void checkBoard(Board expectedBoard, Board actualBoard) {
        assertEquals(expectedBoard.getId(), actualBoard.getId());
        assertEquals(expectedBoard.getTitle(), actualBoard.getTitle());
        assertEquals(expectedBoard.getComments(), actualBoard.getComments());
        checkUser(expectedBoard.getUser(), actualBoard.getUser());
    }

    private void checkUser(User expectedUser, User actualUser) {
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getNickName(), actualUser.getNickName());
    }


}