package com.ssafy.gumibom.domain.pamphlet.repository;

import com.ssafy.gumibom.domain.pamphlet.entity.Pamphlet;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingPamphletRepository implements PamphletRepository {

    private final EntityManager em;

    @Override
    public void save(Pamphlet mPamphlet) {
        em.persist(mPamphlet);
    }

    @Override
    public List<Pamphlet> findByUserId(long id) {
        return null;
    }

    @Override
    public List<Pamphlet> findAll() {
        return em.createQuery("select mp from MeetingPamphlet mp", Pamphlet.class).getResultList();
    }
}
