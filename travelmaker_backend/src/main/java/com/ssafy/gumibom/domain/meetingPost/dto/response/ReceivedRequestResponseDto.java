package com.ssafy.gumibom.domain.meetingPost.dto.response;

import com.ssafy.gumibom.domain.meetingPost.entity.MeetingRequest;
import lombok.Getter;


@Getter
public class ReceivedRequestResponseDto {

    private Long requestId;

    private String acceptorName;

    private String requestorName;
    private String requestorImg;
    private Double requestorBelief;

    private Long meetingPostId;
    private String meetingPostTitle;

    public ReceivedRequestResponseDto(MeetingRequest request) {
        this.requestId = request.getId();

        this.acceptorName = request.getAcceptor().getNickname();

        this.requestorName = request.getRequestor().getNickname();
        this.requestorImg = request.getRequestor().getImgURL();
        this.requestorBelief = request.getRequestor().getBelief();

        this.meetingPostId = request.getMeetingPost().getId();
        this.meetingPostTitle = request.getMeetingPost().getTitle();
    }
}
