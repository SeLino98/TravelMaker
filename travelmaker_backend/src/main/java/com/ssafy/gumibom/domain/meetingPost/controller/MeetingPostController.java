package com.ssafy.gumibom.domain.meetingPost.controller;

import com.ssafy.gumibom.domain.meetingPost.dto.WriteMeetingPostRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.service.MeetingPostService;
import com.ssafy.gumibom.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meeting-post")
@RequiredArgsConstructor
public class MeetingPostController {

    private final UserService userService;
    private final MeetingPostService meetingPostService;

    @PostMapping
    public String writeMeetingPost(@RequestBody WriteMeetingPostRequestDTO requestDTO) {

        meetingPostService.write(requestDTO);
        return "redirect:/meeting-post";
    }

//    @GetMapping("/{meetingPostId}")
//    public String clickMarker(@PathVariable("meetingPostId") Long meetingPostId) {
//
//        meetingPostService.meetingPostDetail(meetingPostId);
//    }
}
