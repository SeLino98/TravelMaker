package com.ssafy.gumibom.domain.user.repository;

import com.ssafy.gumibom.domain.user.entity.User;
import jakarta.persistence.EntityManager;
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

    public List<User> findByUsername(String username){
        return em.createQuery("select  u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
    }

//    public void update(User user) {
//
//    }


}
