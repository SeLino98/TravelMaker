package com.ssafy.gumibom.domain.record.dto.response;

import com.ssafy.gumibom.global.base.BaseResponseDto;
import lombok.Getter;

@Getter
public class MakePersonalRecordResponseDto extends BaseResponseDto {

    private Long recordId;

    public MakePersonalRecordResponseDto(Boolean isSuccess, String message) {
        super(isSuccess, message);
    }
}
