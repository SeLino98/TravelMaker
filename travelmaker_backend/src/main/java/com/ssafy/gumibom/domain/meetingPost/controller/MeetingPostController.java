package com.ssafy.gumibom.domain.meetingPost.controller;

import com.ssafy.gumibom.domain.meetingPost.dto.FindByGeoRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.service.MeetingPostService;
import com.ssafy.gumibom.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Meeting Post", description = "모임글 관련 api")
@RestController
@RequestMapping("/meeting-post")
@RequiredArgsConstructor
public class MeetingPostController {

    private final UserService userService;
    private final MeetingPostService meetingPostService;

    // 모임글 작성
    @Operation(summary = "모임글 작성")
    @PostMapping("/write")
    public ResponseEntity<?> writeMeetingPost(@RequestBody WriteMeetingPostRequestDTO requestDTO) {

//        meetingPostService.write(requestDTO);
//        return "redirect:/meeting-post";
        return meetingPostService.write(requestDTO);
    }

    @Operation(summary = "위치 기반 모임글 리스트 필터링 조회")
    @PostMapping
    public ResponseEntity<?> inquiryMeetingPost(@RequestBody FindByGeoRequestDTO requestDTO) {

        return meetingPostService.meetingPostRadius(requestDTO.getLatitude(), requestDTO.getLongitude(), requestDTO.getRadius(), requestDTO.getCategories());
    }

    @Operation(summary = "마커 클릭 시 모임글 상세 조회")
    @GetMapping("/{meetingPostId}")
    public ResponseEntity<?> clickMarker(@PathVariable("meetingPostId") Long meetingPostId) {

        return meetingPostService.meetingPostDetail(meetingPostId);
    }

    @Operation(summary = "모임글 수정")
    @PutMapping("/{meetingPostId}")
    public ResponseEntity<?> modifyMeetingPost(@RequestBody WriteMeetingPostRequestDTO requestDTO, @PathVariable Long meetingPostId) {

        return meetingPostService.modify(requestDTO, meetingPostId);
    }

    @Operation(summary = "모임글 삭제")
    @DeleteMapping("/{meetingPostId}")
    public ResponseEntity<?> deleteMeetingPost(@PathVariable Long meetingPostId) {

        meetingPostService.delete(meetingPostId);
        return ResponseEntity.ok("모임글이 삭제되었습니다.");
    }

}
