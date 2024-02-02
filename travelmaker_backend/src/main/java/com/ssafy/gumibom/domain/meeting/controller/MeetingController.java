package com.ssafy.gumibom.domain.meeting.controller;

import com.ssafy.gumibom.domain.meeting.dto.MeetingReqDto;
import com.ssafy.gumibom.domain.meeting.dto.MeetingResDto;
import com.ssafy.gumibom.domain.meeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping
    public ResponseEntity<Long> createMeeting(@RequestBody MeetingReqDto requestDTO) {
        Long id = meetingService.createMeeting(requestDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingResDto> getMeeting(@PathVariable Long id) {
        MeetingResDto meetingResponseDTO = meetingService.getMeeting(id);
        return ResponseEntity.ok(meetingResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeetingResDto> updateMeeting(@PathVariable Long id, @RequestBody MeetingReqDto requestDTO) {
        MeetingResDto meetingResponseDTO = meetingService.updateMeeting(id, requestDTO);
        return ResponseEntity.ok(meetingResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.ok().build();
    }
}
