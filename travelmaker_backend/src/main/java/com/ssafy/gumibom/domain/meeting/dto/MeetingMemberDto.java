package com.ssafy.gumibom.domain.meeting.dto;

import com.ssafy.gumibom.domain.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeetingMemberDto {
    private Long id;
    private Boolean isNative;
    private Boolean isHead;

    private UserDto user;
    private MeetingDto meeting;
}
