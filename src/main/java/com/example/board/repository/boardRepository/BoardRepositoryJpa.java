package com.example.board.repository.boardRepository;

import com.example.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryJpa implements BoardRepository {
    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Optional<Board> findOne(Long boardId) {
        return Optional.ofNullable(em.find(Board.class, boardId));
    }

    public List<Board> findAll() {
        return em.createQuery("select b" +
                " from Board b" +
                " join fetch b.user", Board.class)
                .getResultList();
    }

    @Override
    public void delete(Long boardId) {
        Optional<Board> findedBoard = findOne(boardId);
        findedBoard.ifPresent(em::remove);
    }
}
