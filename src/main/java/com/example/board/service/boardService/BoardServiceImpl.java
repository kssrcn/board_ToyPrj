package com.example.board.service.boardService;

import com.example.board.domain.Board;
import com.example.board.repository.boardRepository.BoardRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long register(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    @Override
    @Transactional
    public void update(Long boardId, String content) {
        Board findedBoard = findOne(boardId);
        findedBoard.changetContent(content);
    }

    @Override
    @Transactional
    public void delete(Long boardId) {
        boardRepository.delete(boardId);
    }

    @Override
    public Board findOne(Long boardId) {
        Optional<Board> findedBoard = boardRepository.findOne(boardId);
        return findedBoard.orElseThrow(() -> new NoResultException("게시글이 존재하지 않습니다."));
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
