package com.ssafy.gumibom.domain.meetingPost.dto.response;

import com.ssafy.gumibom.domain.meetingPost.entity.MeetingRequest;
import lombok.Getter;


@Getter
public class ReceivedRequestResponseDto {

    private String acceptorName;

    private String requestorName;
    private String requestorImg;
    private Double requestorBelief;

    private String meetingPostTitle;

    public ReceivedRequestResponseDto(MeetingRequest request) {

        this.acceptorName = request.getAcceptor().getNickname();

        this.requestorName = request.getRequestor().getNickname();
        this.requestorImg = request.getRequestor().getImgURL();
        this.requestorBelief = request.getRequestor().getBelief();

        this.meetingPostTitle = request.getMeetingPost().getTitle();
    }
}
