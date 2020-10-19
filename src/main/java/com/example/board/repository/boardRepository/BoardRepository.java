package com.example.board.repository.boardRepository;

import com.example.board.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    void save(Board board);

    Optional<Board> findOne(Long boardId);

    List<Board> findAll();

    void delete(Long boardId);
}
