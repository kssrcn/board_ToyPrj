package com.example.board.service.boardService;

import com.example.board.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Long register(Board board);

    void update(Long boardId, String content);

    void delete(Long boardId);

    Board findOne(Long boardId);

    List<Board> findAll();
}
