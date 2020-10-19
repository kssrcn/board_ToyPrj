package com.example.board.api;


import com.example.board.api.dtos.boardDto.BoardDetailFindDto;
import com.example.board.api.dtos.boardDto.BoardFindDto;
import com.example.board.api.dtos.boardDto.BoardRegisterDto;
import com.example.board.api.dtos.boardDto.BoardUpdateDto;
import com.example.board.domain.Board;
import com.example.board.service.boardService.BoardService;
import com.example.board.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;
    private final UserService userService;


    //전체 조회
    @GetMapping("/boards")
    public Result<List<BoardFindDto>> findAll() {
        List<Board> all = boardService.findAll();
        return new Result<>(all.stream()
                .map(board -> new BoardFindDto(board.getId(), board.getTitle(), board.getBoardDate(), board.getUser().getNickName()))
                .collect(Collectors.toList()));
    }

    //게시글 단건 조회
    @GetMapping("/board/{id}")
    public BoardDetailFindDto findOne(@PathVariable("id") Long boardId) {
        Board findedBoard = boardService.findOne(boardId);
        return new BoardDetailFindDto(findedBoard.getId(), findedBoard.getTitle()
                , findedBoard.getBoardDate(), findedBoard.getUser().getNickName(),findedBoard.getContent()
        ,findedBoard.getComments());
    }

    //게시글 수정
    @PutMapping("/board/{id}")
    public void updateBoard(@PathVariable("id") Long boardId, @RequestBody BoardUpdateDto boardUpdateDto) {
        boardService.update(boardId, boardUpdateDto.getBoardContent());
    }

    //게시글 삭제
    @DeleteMapping("/board/{id}")
    public void deleteBoard(@PathVariable("id") Long boardId) {
        boardService.delete(boardId);
    }

    //게시글 등록
    @PostMapping("/board")
    public void register(@RequestBody BoardRegisterDto boardRegisterDto) {
        Board board = Board.createBoard(userService.findOne(boardRegisterDto.getUserId()),
                boardRegisterDto.getBoardTitle(), boardRegisterDto.getBoardContent());
        boardService.register(board);
    }

}

