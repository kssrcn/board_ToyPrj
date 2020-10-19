package com.example.board.service.userService;

import com.example.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Long join(User user);
    void delete(Long userId);
    User findOne(Long userId);
    List<User> findAll();
}
