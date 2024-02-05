package com.ssafy.gumibom.domain.meeting.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDetailReqDto {
    private Long meetingId;
    private Long meetingPostId;
}
