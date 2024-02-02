package com.ssafy.gumibom.domain.meeting.repository;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@RequiredArgsConstructor
public class MeetingRepository {

//    private final EntityManager em;
//
//    public void save(Meeting meeting) {
//        em.persist(meeting);
//    }
//
//    /**
//     * 특정 유저의 모임 조회
//     *
//     * @param userId
//     */
//    public List<Meeting> findByMemberId(Long userId) {
//
//        // userRepository 코드로 수정 필요
////        List<MeetingMember> meetingMemberList = em.find(User.class, userId).getMeetingMemberList();
////        return meetingMemberList.stream().map(MeetingMember::getMeeting).toList();
//        return em.createQuery("select mm.meeting from MeetingMember mm " +
//                        "where mm.user.id = :userId ")
//                .getResultList();
//    }
//
//    public List<Meeting> findAll() {
//        return em.createQuery("select m from Meeting m", Meeting.class).getResultList();
//    }
//
//    public interface MeetingRepository extends JpaRepository<Meeting, Long> {
//        @Query("SELECT mm.meeting FROM MeetingMember mm WHERE mm.user.id = :userId")
//        List<Meeting> findByUserId(@Param("userId") Long userId);
//
//    }
}
