package com.ssafy.gumibom.domain.meetingPost.repository;

import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingPostRepository {

    private final EntityManager em;

    public void save(MeetingPost meetingPost) {
        em.persist(meetingPost);
    }

    // 마커를 클릭했을 때 해당 모임글 상세 정보
    public MeetingPost findOne(Long id) {
        return em.find(MeetingPost.class, id);
    }

    // 선택 (기준 위치, 근방 몇 km까지) 한 영역에 올라온 모임글
    public List<MeetingPost> findByGeo(Double latitude, Double longitude, Integer upToKm) {

        // deadline과 비교하여 지났으면 status(마감여부)를 true로 변경
        em.createQuery("UPDATE MeetingPost m SET m.status = true " +
                        "WHERE m.deadline < CURRENT_TIMESTAMP ")
                .executeUpdate();

       em.flush();
       em.clear();

        // 위도 경도 따와서 기준 위치 근방 km 안인지, 아직 모집 중인지 확인 후 select

        return em.createQuery("SELECT m, " +
                        "(6371 * acos(" +
                        "cos(radians(:latitude)) * cos(radians(m.position.latitude)) * cos(radians(m.position.longitude) - radians(:longitude)) " +
                        "+ sin(radians(:latitude)) * sin(radians(m.position.latitude)) " +
                        ")) AS distance " +
                        "FROM MeetingPost m " +
                        "WHERE m.status = false " +
                        "HAVING distance <= :upToKm ", MeetingPost.class)
                .setParameter("latitude", latitude)
                .setParameter("longitude", longitude)
                .setParameter("upToKm", upToKm)
                .getResultList();
    }
}
