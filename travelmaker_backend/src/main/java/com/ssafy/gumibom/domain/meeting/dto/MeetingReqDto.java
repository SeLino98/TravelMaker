package com.ssafy.gumibom.domain.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeetingReqDto {
    private List<Long> categoryIds;
    private String imgUrl;
    private Boolean status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
