package com.ssafy.gumibom.domain.meetingPost.service;

import com.ssafy.gumibom.domain.meetingPost.dto.request.RequestJoinMeetingRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingRequest;
import com.ssafy.gumibom.domain.meetingPost.repository.MeetingPostRepository;
import com.ssafy.gumibom.domain.meetingPost.repository.MeetingRequestRepository;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import com.ssafy.gumibom.global.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingRequestService {

    private final UserRepository userRepository;
    private final MeetingPostRepository meetingPostRepository;
    private final MeetingRequestRepository meetingRequestRepository;

    private final FcmService fcmService;


    @Transactional
    public ResponseEntity<?> requestJoin(RequestJoinMeetingRequestDTO rJMRDto) throws IOException {

        User requestor = userRepository.findOne(rJMRDto.getRequestorId());
        MeetingPost meetingPost = meetingPostRepository.findOne(rJMRDto.getMeetingPostId());
        User acceptor = meetingPost.getHead();

        MeetingRequest meetingRequest = MeetingRequest.createRequest(meetingPost, requestor, acceptor);

        fcmService.sendMessageTo(acceptor.getFcmtoken(), "모임 요청", requestor.getUsername()+"님이 모임 요청을 보냈습니다.");

        meetingRequestRepository.save(meetingRequest);

        return ResponseEntity.ok("모임 요청이 완료되었습니다.");
    }
}
