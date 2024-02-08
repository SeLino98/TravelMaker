package com.ssafy.gumibom.domain.meeting.dto.response;

import com.ssafy.gumibom.domain.meeting.entity.MeetingMember;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MeetingResDto {
    private Long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String imgUrl;
    private Boolean isFinish;
    private List<MeetingMember> members;

}
