package com.ssafy.gumibom.domain.meetingPost.service;

import com.ssafy.gumibom.domain.meetingPost.dto.request.RequestJoinMeetingRequestDTO;
import com.ssafy.gumibom.domain.meetingPost.dto.request.ResAboutReqJoinMeetingRequestDto;
import com.ssafy.gumibom.domain.meetingPost.dto.response.ReceivedRequestResponseDto;
import com.ssafy.gumibom.domain.meetingPost.dto.response.SentRequestResponseDto;
import com.ssafy.gumibom.domain.meetingPost.dto.response.ShowAllJoinRequestResponseDto;
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
import java.util.List;
import java.util.stream.Collectors;

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

    public ShowAllJoinRequestResponseDto showAllJoinRequest(Long userId) {

        List<MeetingRequest> sentRequests = meetingRequestRepository.findSentByUserId(userId);
        List<MeetingRequest> receivedRequests = meetingRequestRepository.findReceivedByUserId(userId);

        List<ReceivedRequestResponseDto> receivedRequestsDto = receivedRequests.stream()
                .map(req -> new ReceivedRequestResponseDto(req))
                .collect(Collectors.toList());

        List<SentRequestResponseDto> sentRequestsDto = sentRequests.stream()
                .map(req -> new SentRequestResponseDto(req))
                .collect(Collectors.toList());

        return new ShowAllJoinRequestResponseDto(receivedRequestsDto, sentRequestsDto);
    }

    public ResponseEntity<?> acceptRequest(ResAboutReqJoinMeetingRequestDto rARJMRDto) throws IOException {
        User requestor = userRepository.findOne(rARJMRDto.getRequestorId());
        MeetingPost meetingPost = meetingPostRepository.findOne(rARJMRDto.getMeetingPostId());
        MeetingRequest request = meetingRequestRepository.findOne(rARJMRDto.getRequestId());

        meetingPost.addApplier(requestor, false, null); // 게시글에 meetingApplier로 추가
        fcmService.sendMessageTo(requestor.getFcmtoken(), "모임 승낙", meetingPost.getTitle()+" 모임에 참여자가 되었습니다."); // FCM 토큰으로 노티 전달
        meetingRequestRepository.delete(request);

        return ResponseEntity.ok("모임 요청을 승낙했습니다.");
    }
}
