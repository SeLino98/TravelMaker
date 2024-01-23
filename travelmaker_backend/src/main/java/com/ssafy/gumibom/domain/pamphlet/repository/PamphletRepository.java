package com.ssafy.gumibom.domain.pamphlet.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PamphletRepository {

    public void save();
    public void findOne();
    public void findAll();
}
