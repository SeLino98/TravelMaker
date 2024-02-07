package com.ssafy.gumibom.domain.meeting.service;


import com.ssafy.gumibom.domain.meeting.dto.res.MeetingResDto;
import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import com.ssafy.gumibom.domain.meeting.repository.MeetingMemberRepository;
import com.ssafy.gumibom.domain.meeting.repository.MeetingRepository;
import com.ssafy.gumibom.domain.meeting.repository.MeetingRepositoryQuery;
import com.ssafy.gumibom.domain.meetingPost.dto.DetailMeetingPostResForMeetingDto;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingApplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) // 전역으로 readOnly = true 선언,데이터 쓰기 시에만 @Transactional 추가
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;
    private final MeetingRepositoryQuery meetingRepositoryQuery;

    public MeetingService(MeetingRepository meetingRepository, MeetingMemberRepository meetingMemberRepository, MeetingRepositoryQuery meetingRepositoryQuery) {
        this.meetingRepository = meetingRepository;
        this.meetingMemberRepository = meetingMemberRepository;
        this.meetingRepositoryQuery = meetingRepositoryQuery;
    }

    public List<MeetingResDto> getMeetingsByUserId(Long userId) {
        // 사용자 ID로 Meeting 엔티티 검색
        List<Meeting> meetings = meetingRepositoryQuery.findByUserId(userId);

        // Meeting 엔티티를 MeetingResDto로 변환
        return meetings.stream()
                .map(this::convertToMeetingResDto)
                .collect(Collectors.toList());
    }

    private MeetingResDto convertToMeetingResDto(Meeting meeting) {
        return MeetingResDto.builder()
                .id(meeting.getId())
                .title(meeting.getTitle())
                .startDate(meeting.getStartDate())
                .endDate(meeting.getEndDate())
                .imgUrl(meeting.getImgUrl())
                .build();
    }


    @Transactional
    public Long createMeeting(DetailMeetingPostResForMeetingDto detailMeetingPostResForMeetingDto) {
        // Meeting 엔티티 생성
        System.out.println(detailMeetingPostResForMeetingDto);

        Meeting meeting = new Meeting(detailMeetingPostResForMeetingDto);
        meetingRepository.save(meeting);

        // MeetingApplier 리스트를 MeetingMember 리스트로 변환
        List<MeetingMember> members = convertAppliersToMembers(detailMeetingPostResForMeetingDto.getMembers(), meeting);

        // 각 MeetingMember를 저장
        members.forEach(member -> {
            meetingMemberRepository.save(member);
        });

        return meeting.getId();
    }

    private List<MeetingMember> convertAppliersToMembers(List<MeetingApplier> appliers, Meeting meeting) {
        return appliers.stream()
                .map(applier -> createMeetingMemberFromApplier(applier, meeting))
                .collect(Collectors.toList());
    }

    private MeetingMember createMeetingMemberFromApplier(MeetingApplier applier, Meeting meeting) {
        MeetingMember member = new MeetingMember();
        member.setUser(applier.getUser());
        member.setMeeting(meeting);
        meeting.getMeetingMembers().add(member);
        // 여기에서 MeetingMember의 다른 필드를 설정할 수 있습니다.
        if(applier.getUser().getTown().equals(applier.getMeetingPost().getPosition().getTown())){
            member.setIsNative(true);
        } else{
            member.setIsNative(false);
        }
        return member;
    }

    @Transactional
    public Boolean getMeetingByMeetingId(Long meetingId) {
        Meeting meeting = meetingRepositoryQuery.findByMeetingId(meetingId);
        meeting.finishMeeting();
        return meeting.getStatus();
    }

}
