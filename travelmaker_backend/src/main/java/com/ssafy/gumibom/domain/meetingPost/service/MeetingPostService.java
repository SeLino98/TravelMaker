package com.ssafy.gumibom.domain.meetingPost.service;

import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import com.ssafy.gumibom.domain.meetingPost.repository.MeetingPostRepository;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import com.ssafy.gumibom.global.common.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    // 상세 조회할 때 dDay 계산이랑 deadLine 비교해서 status update 다 해줘야함 = dto를 만들어야함
    @Transactional
    public ResponseEntity<?> meetingPostDetail(Long id) {
        return ResponseEntity.ok(meetingPostRepository.findOne(id));
    }

    // 반경 3 km 안에 존재하는 모임글들의 정보 반환 // 위치랑 meetingPost id만 주면 될듯
    @Transactional
    public ResponseEntity<?> meetingPostRadius(Double latitude, Double longitude, Integer radius) {
        return ResponseEntity.ok(meetingPostRepository.findByGeo(latitude, longitude, radius));
    }
}
