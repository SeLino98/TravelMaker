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

    public List<Pamphlet> findByUserId(Long id) {
        return em.createQuery("select pp from PersonalPamphlet pp " +
                "where pp.user.id = id", Pamphlet.class).getResultList();
    }

    public List<Pamphlet> findAll() {
        return em.createQuery("select pp from PersonalPamphlet pp ", Pamphlet.class).getResultList();
    }
}
