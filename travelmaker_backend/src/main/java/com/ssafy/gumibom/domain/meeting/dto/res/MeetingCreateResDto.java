package com.ssafy.gumibom.domain.meeting.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MeetingCreateResDto {
    private boolean isSucess;
    private String message;

}
