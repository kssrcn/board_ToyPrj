package com.example.board.repository.userRepository;

import com.example.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void delete(Long userId);

    Optional<User> findOne(Long userId);

    List<User> findAll();

    List<User> findByNickName(String nickName);
}
