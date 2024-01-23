package com.ssafy.gumibom.domain.pamphlet.repository;

import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonalPamphletRepository implements PamphletRepository {

    private final EntityManager em;

    @Override
    public void save(Pamphlet pPamphlet) {
        em.persist(pPamphlet);
    }

    @Override
    public List<Pamphlet> findByUserId(long id) {
        return em.createQuery("select pp from PersonalPamphlet pp " +
                "where pp.user.id = id", Pamphlet.class).getResultList();
    }

    @Override
    public List<Pamphlet> findAll() {
        return em.createQuery("select pp from PersonalPamphlet pp ", Pamphlet.class).getResultList();
    }
}
