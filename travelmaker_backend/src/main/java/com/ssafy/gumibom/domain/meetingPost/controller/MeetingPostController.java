package com.ssafy.gumibom.domain.meetingPost.controller;

import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.service.MeetingPostService;
import com.ssafy.gumibom.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meeting-post")
@RequiredArgsConstructor
public class MeetingPostController {

    private final UserService userService;
    private final MeetingPostService meetingPostService;

    // 모임글 작성
    @PostMapping
    public ResponseEntity<?> writeMeetingPost(@RequestBody WriteMeetingPostRequestDTO requestDTO) {

//        meetingPostService.write(requestDTO);
//        return "redirect:/meeting-post";
        return meetingPostService.write(requestDTO);
    }

    @GetMapping
    public ResponseEntity<?> inquiryMeetingPost(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam(defaultValue = "3") Double radius, @RequestParam(defaultValue = "taste, healing, culture, active, picture, nature") List<String> categories) {

        return meetingPostService.meetingPostRadius(latitude, longitude, radius, categories);
    }

    @GetMapping("/{meetingPostId}")
    public ResponseEntity<?> clickMarker(@PathVariable("meetingPostId") Long meetingPostId) {

        return meetingPostService.meetingPostDetail(meetingPostId);
    }


}
