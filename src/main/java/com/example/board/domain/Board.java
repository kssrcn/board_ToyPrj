package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private LocalDateTime boardDate;
    private String title;
    @Column(name = "board_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public static Board createBoard(User user, String title, String content) {
        Board board = new Board();
        board.user = user;
        board.title = title;
        board.content = content;
        board.boardDate = LocalDateTime.now();
        return board;
    }

    public void changetContent(String content) {
        this.content = content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
