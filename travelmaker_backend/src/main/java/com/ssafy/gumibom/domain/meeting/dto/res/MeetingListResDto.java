package com.ssafy.gumibom.domain.meeting.dto.res;

import com.ssafy.gumibom.domain.meeting.entity.Meeting;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeetingListResDto {
    private List<MeetingResDto> meetings;
}
