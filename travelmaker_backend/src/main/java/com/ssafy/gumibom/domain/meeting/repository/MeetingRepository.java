package com.ssafy.gumibom.domain.meeting.repository;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepository {

    private final EntityManager em;

    public void save(Meeting meeting) {
        em.persist(meeting);
    }

    /**
     * 특정 유저의 모임 조회
     * @param userId
     */
    public List<Meeting> findByMemberId(Long userId) {
        // userRepository 코드로 수정 필요
        List<MeetingMember> meetingMemberList = em.find(User.class, userId).getMeetingMemberList();
        return meetingMemberList.stream().map(MeetingMember::getMeeting).toList();
    }

    public List<Meeting> findAll() {
        return em.createQuery("select m from Meeting m", Meeting.class).getResultList();
    }
}
