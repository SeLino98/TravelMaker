package com.ssafy.gumibom.domain.meeting.dto.response;

import com.ssafy.gumibom.global.base.BaseResponseDto;
import lombok.*;



@Getter
public class MeetingCreateResDto extends BaseResponseDto {
    public MeetingCreateResDto(Boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    @Override
    public Boolean getIsSuccess() {
        return super.getIsSuccess();
    }

}
