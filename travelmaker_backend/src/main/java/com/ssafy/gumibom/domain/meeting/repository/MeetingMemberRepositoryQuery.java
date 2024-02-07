//package com.ssafy.gumibom.domain.meeting.repository;
//
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class MeetingMemberRepositoryQuery {
//    private final EntityManager em;
//
//    public Long countByIsNativeTrue(Long meetingPostId) {
//        return em.createQuery(
//                        "SELECT COUNT(ma) FROM MeetingApplier ma " +
//                                "WHERE ma.meetingPost.id = :meetingPostId AND ma.isNative = TRUE", Long.class)
//                .setParameter("meetingPostId", meetingPostId)
//                .getSingleResult();
//    }
//
//    public Long countByIsNativeFalse(Long meetingPostId) {
//        return em.createQuery(
//                        "SELECT COUNT(ma) FROM MeetingApplier ma " +
//                                "WHERE ma.meetingPost.id = :meetingPostId AND ma.isNative = FALSE", Long.class)
//                .setParameter("meetingPostId", meetingPostId)
//                .getSingleResult();
//    }
//}
