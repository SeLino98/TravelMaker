package com.ssafy.gumibom.domain.meetingPost.service;

import com.ssafy.gumibom.domain.meetingPost.dto.DetailOfMeetingPostResponseDTO;
import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import com.ssafy.gumibom.domain.meetingPost.repository.MeetingPostRepository;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingPostService {

    private final UserRepository userRepository;
    private final MeetingPostRepository meetingPostRepository;


    // 모임글 생성

    @Transactional
    public ResponseEntity<?> write(WriteMeetingPostRequestDTO writeMeetingPostRequestDTO) {

        User author = userRepository.findByUsername(writeMeetingPostRequestDTO.getUsername());

        MeetingPost meetingPost = MeetingPost.createMeetingPost(writeMeetingPostRequestDTO, author);

        meetingPostRepository.save(meetingPost);

        return ResponseEntity.ok("모임글 작성 성공");
    }


    // 마커 클릭
    @Transactional
    public ResponseEntity<?> meetingPostDetail(Long id) {

        DetailOfMeetingPostResponseDTO responseDTO = new DetailOfMeetingPostResponseDTO();
        responseDTO.setMeetingPost(meetingPostRepository.findOne(id));
        responseDTO.setDDay(ChronoUnit.DAYS.between(responseDTO.getMeetingPost().getDeadline(), LocalDateTime.now())-1);

        return ResponseEntity.ok(responseDTO);
    }

    // 반경 n km 안에 존재하는 모임글들의 정보 반환 // 위치랑 meetingPost id
    @Transactional
    public ResponseEntity<?> meetingPostRadius(Double latitude, Double longitude, Double radius, List<String> categories) {

        return ResponseEntity.ok(meetingPostRepository.findByGeo(latitude, longitude, radius, categories));
    }
}
