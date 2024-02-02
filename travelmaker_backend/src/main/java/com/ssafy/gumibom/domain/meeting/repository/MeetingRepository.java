package com.ssafy.gumibom.domain.meeting.repository;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query("SELECT mm.meeting FROM MeetingMember mm WHERE mm.user.id = :userId")
    List<Meeting> findByUserId(@Param("userId") Long userId);
}
