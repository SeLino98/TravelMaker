package com.ssafy.gumibom.domain.meeting.repository;

import com.ssafy.gumibom.domain.meeting.dto.res.MeetingDetailResDto;
import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

//    MeetingDetailResDto findByIdAndMeetingPostId(Long meetingId, Long meetingPostId);

//    public List<Meeting> findByUserId(Long userId);
}


