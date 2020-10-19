package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private LocalDateTime commentDate;
    @Column(name = "comment_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public static Comment createComment(Board board, User user, String content) {
        Comment comment = new Comment();
        comment.board = board;
        comment.user = user;
        comment.commentDate = LocalDateTime.now();
        comment.content = content;
        board.addComment(comment);
        return comment;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
