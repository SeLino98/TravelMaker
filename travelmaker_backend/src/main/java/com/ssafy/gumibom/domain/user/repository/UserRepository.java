package com.ssafy.gumibom.domain.user.repository;

import com.ssafy.gumibom.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user){
        if(user.getId() == null){
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public void remove(User user){
        em.remove(user);
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult(); // 리스트 대신 단일 결과를 가져옵니다.
        } catch (NoResultException e) {
            return null; // 결과가 없으면 null을 반환합니다.
        } catch (NonUniqueResultException e) {
            throw new IllegalStateException("중복된 사용자 이름이 있습니다."); // 중복 결과 처리
        }
    }


//    public void update(User user) {
//
//    }


}
