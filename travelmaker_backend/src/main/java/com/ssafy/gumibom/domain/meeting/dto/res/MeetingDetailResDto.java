package com.ssafy.gumibom.domain.meeting.dto.res;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import com.ssafy.gumibom.domain.meetingPost.entity.MeetingPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MeetingDetailResDto {
    private MeetingPost meetingPost;
    private Meeting meeting;
}
