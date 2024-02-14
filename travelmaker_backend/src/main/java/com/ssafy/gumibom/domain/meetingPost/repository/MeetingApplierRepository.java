package com.ssafy.gumibom.domain.meetingPost.repository;

import com.ssafy.gumibom.domain.meetingPost.entity.MeetingApplier;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingApplierRepository {

    private final EntityManager em;

    public void save(MeetingApplier meetingApplier) {
        em.persist(meetingApplier);
    }

    // 모임글 별 참여자 조회
    public List<MeetingApplier> findByMeetingPost(MeetingPost meetingPost, Boolean containHead, Boolean containGeneral) {
        return em.createQuery("select ma from MeetingApplier ma" +
                                " where ma.meetingPost = :meetingPost" +
                                (containHead ? (containGeneral ? "" : " and ma.isHead = TRUE") : " and ma.isHead = FALSE")
                        , MeetingApplier.class)
                .setParameter("meetingPost", meetingPost)
                .getResultList();
    }


    //meetingPostId와 userId로 meetingApplier를 찾아서 삭제
    public void leaveMeetingByUserIdAndMeetingPostId(Long userId, Long meetingPostId) {
        em.createQuery("DELETE FROM MeetingApplier ma WHERE ma.user.id = :userId AND ma.meetingPost.id = :meetingPostId")
                .setParameter("userId", userId)
                .setParameter("meetingPostId", meetingPostId)
                .executeUpdate();
    }

    public void leaveMeetingByUserIdAndMeetingPostId(MeetingApplier applier) {
        em.remove(applier);
    }


}
