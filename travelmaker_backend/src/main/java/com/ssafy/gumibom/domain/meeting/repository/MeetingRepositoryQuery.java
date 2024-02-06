package com.ssafy.gumibom.domain.meeting.repository;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepositoryQuery {
    private final EntityManager em;

    public List<Meeting> findByUserId(Long userId) {
        return em.createQuery(
                        "SELECT m FROM Meeting m " +
                                "JOIN MeetingMember mm ON m.id = mm.meeting.id " +
                                "WHERE mm.user.id = :userId", Meeting.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public Meeting findByMeetingId(Long id){
        return em.find(Meeting.class, id);
    }
}


