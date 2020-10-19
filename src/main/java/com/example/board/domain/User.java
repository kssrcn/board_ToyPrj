package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String nickName;

    @Column(name = "user_name")
    private String name;

    public static User createUser(String nickName, String name) {
        User user = new User();
        user.nickName = nickName;
        user.name = name;

        return user;
    }

}
