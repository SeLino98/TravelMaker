package com.ssafy.gumibom.domain.record.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordRepository {

    private final EntityManager em;

    public void save(Record record) {
        em.persist(record);
    }

    public Record findOne(Long id) {
        return em.find(Record.class, id);
    }

    public List<Record> findAll() {
        return em.createQuery("select r from Record r", Record.class).getResultList();
    }
}
