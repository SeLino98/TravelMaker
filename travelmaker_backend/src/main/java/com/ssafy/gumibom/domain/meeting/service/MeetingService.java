package com.ssafy.gumibom.domain.meeting.service;


import com.ssafy.gumibom.domain.meeting.dto.MeetingReqDto;
import com.ssafy.gumibom.domain.meeting.dto.MeetingResDto;
import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) // 전역으로 readOnly = true 선언,데이터 쓰기 시에만 @Transactional 추가
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Transactional
    public Long createMeeting(MeetingReqDto requestDTO) {
        // DTO를 엔티티로 변환하는 로직이 필요합니다.
        // 이 부분에서 카테고리 엔티티 리스트를 조회하거나 생성해야 할 수 있습니다.
        Meeting meeting = new Meeting();
        // meeting에 필요한 정보를 requestDTO로부터 받아와 설정합니다.
        return meetingRepository.save(meeting).getId();
    }

    @Transactional(readOnly = true)
    public MeetingResDto getMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 모임을 찾을 수 없습니다."));
        return new MeetingResDto(meeting);
    }

    @Transactional
    public MeetingResDto updateMeeting(Long id, MeetingReqDto requestDTO) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 모임을 찾을 수 없습니다."));

        // Meeting 엔티티의 필드를 requestDTO를 사용하여 업데이트합니다.
        // 이 부분에서 변환 로직이 필요합니다. 예를 들어, 카테고리 목록을 업데이트 할 수 있습니다.
        meetingRepository.save(meeting);

        return new MeetingResDto(meeting);
    }

    @Transactional
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MeetingResDto> getMeetingsByUserId(Long userId) {
        List<Meeting> meetings = meetingMemberRepository.findMeetingsByUserId(userId);
        return meetings.stream()
                .map(meeting -> new MeetingResDto(meeting))
                .collect(Collectors.toList());
    }
}
