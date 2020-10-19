package com.example.board.service.userService;

import com.example.board.domain.User;
import com.example.board.exception.DuplicatedNickNameException;
import com.example.board.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public Long join(User user) {
        validateDuplicatedNickName(user);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public User findOne(Long userId) {
        Optional<User> findedUser = userRepository.findOne(userId);
        return findedUser.orElseThrow(() -> new NoResultException("회원이 존재하지 않습니다."));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void validateDuplicatedNickName(User user) {
        List<User> duplicatedNickNames = userRepository.findByNickName(user.getNickName());
        if (!duplicatedNickNames.isEmpty()) {
            throw new DuplicatedNickNameException("이미 등록된 닉네임 입니다.");
        }
    }
}
