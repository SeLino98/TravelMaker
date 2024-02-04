package com.ssafy.gumibom.domain.pamphlet.repository;

import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonalPamphletRepository {

    private final EntityManager em;

    public Long save(PersonalPamphlet pPamphlet) {
        em.persist(pPamphlet);
        return pPamphlet.getId();
    }

    public PersonalPamphlet findByPamphletId(Long id) {
//        return em.createQuery(
//                "select pp from PersonalPamphlet pp "+
//                        "join fetch pp.user "+
//                        "join fetch pp.title "+
//                        "join fetch pp.love "+
//                        "join fetch pp.createTime "+
//                        "join fetch pp.personalRecords ppr", PersonalPamphlet.class
//        )
//                .getResultList();
        return em.find(PersonalPamphlet.class, id);
    }

    public List<PersonalPamphlet> findByUserId(Long id) {
        return em.createQuery(
                "select pp from PersonalPamphlet pp " +
                        "join fetch pp.user u " +
                "where u.id = :id "
                        , PersonalPamphlet.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<PersonalPamphlet> findAll() {
        return em.createQuery(
                "select pp from PersonalPamphlet pp " +
                        "join fetch pp.user u "
                , PersonalPamphlet.class).getResultList();
    }
}
