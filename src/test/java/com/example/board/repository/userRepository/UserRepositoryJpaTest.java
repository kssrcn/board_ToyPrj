package com.example.board.repository.userRepository;

import com.example.board.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryJpaTest {

    @Autowired UserRepository userRepository;
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
    public void 저장() {
        userRepository.save(user1);
        Optional<User> findedUser = userRepository.findOne(user1.getId());

        checkUser(user1, findedUser.get());
    }

    @Test
    public void 닉네임으로_조회() {
        userRepository.save(user1);
        List<User> users = userRepository.findByNickName("leaf");
        assertEquals(1, users.size());
        checkUser(user1, users.get(0));
    }

    @Test
    public void 전체조회() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        List<User> findedUsers = userRepository.findAll();
        assertEquals(3, findedUsers.size());
        checkUser(user1,findedUsers.get(0));
        checkUser(user2,findedUsers.get(1));
        checkUser(user3,findedUsers.get(2));
    }

    private void checkUser(User expectedUser, User actualUser) {
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getNickName(), actualUser.getNickName());
    }

}