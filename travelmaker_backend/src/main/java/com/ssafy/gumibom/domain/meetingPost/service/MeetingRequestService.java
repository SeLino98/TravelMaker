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

    @Transactional
    public ResponseEntity<?> resAboutRequest(ResAboutReqJoinMeetingRequestDto rARJMRDto, Boolean isAccept) throws IOException {
        User requestor = userRepository.findOne(rARJMRDto.getRequestorId());
        MeetingPost meetingPost = meetingPostRepository.findOne(rARJMRDto.getMeetingPostId());
        MeetingRequest request = meetingRequestRepository.findOne(rARJMRDto.getRequestId());

        // 이미 응답을 받은 모임 요청이라면 -> 바로 리턴
        if(request.getWhetherGetResponse()) {
            ResponseEntity.badRequest();
        }

        if(isAccept) meetingPost.addApplier(requestor, false, null); // 게시글에 meetingApplier로 추가

        request.getResponse();

        String messageTitle = (isAccept) ? "모임 승낙" : "모임 거절";
        String messageBody = meetingPost.getTitle();
        messageBody += (isAccept) ? " 모임 참여자가 되었습니다." : "모임 요청을 거절당했습니다.";
        String responseMessage = (isAccept) ? "모임 요청을 승낙했습니다." : "모임 요청을 거절했습니다.";

        fcmService.sendMessageTo(requestor.getFcmtoken(), messageTitle, messageBody); // FCM 토큰으로 노티 전달

        return ResponseEntity.ok(responseMessage);
    }
}
