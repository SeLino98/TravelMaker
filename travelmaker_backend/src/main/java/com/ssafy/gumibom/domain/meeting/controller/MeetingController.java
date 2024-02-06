package com.ssafy.gumibom.domain.meeting.controller;

import com.ssafy.gumibom.domain.meeting.dto.req.MeetingCreateReqDto;
import com.ssafy.gumibom.domain.meeting.dto.req.MeetingDetailReqDto;
import com.ssafy.gumibom.domain.meeting.dto.res.MeetingDetailResDto;
import com.ssafy.gumibom.domain.meeting.dto.res.MeetingResDto;
import com.ssafy.gumibom.domain.meeting.service.MeetingService;
import com.ssafy.gumibom.domain.meetingPost.dto.DetailMeetingPostResForMeetingDto;
import com.ssafy.gumibom.domain.meetingPost.service.MeetingPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;
    private final MeetingPostService meetingPostService;

//    @PostMapping("/{meetingPostId}")
//    public ResponseEntity<Long> createMeeting(@PathVariable Long meetingPostId) {
//        DetailMeetingPostResForMeetingDto detailMeetingPostResForMeetingDto = meetingPostService.meetingPostDetailRead(meetingPostId);
//        MeetingCreateReqDto meetingCreateReqDto = new MeetingCreateReqDto(detailMeetingPostResForMeetingDto);
//        Long id = meetingService.createMeeting(meetingCreateReqDto);
//        return ResponseEntity.ok(id);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<MeetingResDto>> getMeetingsByUserId(@PathVariable Long userId) {
//        List<MeetingResDto> meetings = meetingService.getMeetingsByUserId(userId);
//        return ResponseEntity.ok(meetings);
//    }

//    @GetMapping("/{meetingPostId}/{meetingId}")
//    public ResponseEntity<MeetingDetailResDto> meetingDetail(MeetingDetailReqDto meetingDetailReqDto) {
//        MeetingDetailResDto meetingDetailResDto = meetingService.getMeetingByMeetingId(meetingDetailReqDto);
//        return ResponseEntity.ok(meetingDetailResDto);
//    }

}
