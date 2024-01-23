package com.ssafy.gumibom.domain.pamphlet.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MeetingPamphletRepository implements PamphletRepository {

    private final EntityManager em;

    @Override
    public void save() {

    }

    @Override
    public void findOne() {

    }

    @Override
    public void findAll() {

    }
}
