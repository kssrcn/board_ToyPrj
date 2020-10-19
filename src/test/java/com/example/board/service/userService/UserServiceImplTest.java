package com.example.board.service.userService;

import com.example.board.domain.User;
import com.example.board.exception.DuplicatedNickNameException;
import com.example.board.repository.boardRepository.BoardRepository;
import com.example.board.repository.userRepository.UserRepository;
import com.example.board.service.boardService.BoardService;
import com.example.board.service.boardService.BoardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    User user1;
    User user2;
    User user3;


    @Before
    public void setUp() {
        user1 = User.createUser("leaf", "김남현");
        user2 = User.createUser("mmbaby", "이쮼민");
        user3 = User.createUser("godtmdals", "장경숙씨");
    }

    @Test
    public void 회원가입() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findByNickName(Mockito.anyString())).thenReturn(new ArrayList<>());
        UserService userService = new UserServiceImpl(userRepository);
        Long userId = userService.join(user1);

        assertEquals(user1.getId(), userId);
    }

    @Test(expected = DuplicatedNickNameException.class)
    public void 중복된닉네임_회원가입_예외() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<User> duplicatedNickNameUsers = new ArrayList<>();
        User duplicatedUser = User.createUser("leaf", "김허슬");
        duplicatedNickNameUsers.add(duplicatedUser);
        Mockito.when(userRepository.findByNickName(Mockito.anyString())).thenReturn(duplicatedNickNameUsers);
        UserService userService = new UserServiceImpl(userRepository);
        userService.join(user1);

        fail("중복된 닉네임을 가진 회원은 가입이 불가능 해야 합니다.");
    }

    @Test(expected = NoResultException.class)
    public void 존재하지_않는_회원_조회() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(Optional.empty());
        UserService userService = new UserServiceImpl(userRepository);
        userService.findOne(user1.getId());

        fail("회원이 존재하지 않아야 합니다.");
    }

}