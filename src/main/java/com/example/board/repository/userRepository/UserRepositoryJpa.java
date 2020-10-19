package com.example.board.repository.userRepository;

import com.example.board.domain.Board;
import com.example.board.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpa implements UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void delete(Long userId) {
        Optional<User> findedUser = findOne(userId);
        findedUser.ifPresent(em::remove);
    }

    public Optional<User> findOne(Long userId) {
        return Optional.ofNullable(em.find(User.class, userId));
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public List<User> findByNickName(String nickName) {
        return em.createQuery("select u from User u where u.nickName = :nickName", User.class)
                .setParameter("nickName", nickName)
                .getResultList();
    }

}
