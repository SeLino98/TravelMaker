package com.ssafy.gumibom.domain.meeting.service;


import com.ssafy.gumibom.domain.meeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // 전역으로 readOnly = true 선언,데이터 쓰기 시에만 @Transactional 추가
@RequiredArgsConstructor // final 키워드가 있는 필드는 생성자 자동 생성
public class MeetingService {

    private final MeetingRepository meetingRepository;

    /*
    모임 리스트 조회할 때 ResponseDTO에 현지인 수 여행객 수도 같이 보내주세요...
     */
}
