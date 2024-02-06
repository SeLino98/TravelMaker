package com.ssafy.gumibom.domain.meetingPost.repository;

import com.ssafy.gumibom.domain.meetingPost.entity.MeetingRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MeetingRequestRepository {

    private final EntityManager em;

    public void save(MeetingRequest req) {
        em.persist(req);
    }

    public void delete(MeetingRequest req) {
        em.remove(req);
    }
}
