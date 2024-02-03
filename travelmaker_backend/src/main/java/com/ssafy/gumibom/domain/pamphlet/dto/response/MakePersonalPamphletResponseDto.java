package com.ssafy.gumibom.domain.pamphlet.dto.response;

import com.ssafy.gumibom.global.base.BaseResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class MakePersonalPamphletResponseDto extends BaseResponseDto {

    private long pamphletId;

    public MakePersonalPamphletResponseDto(Boolean isSuccess, String message, Long pamphletId) {
        super(isSuccess, message);
        this.pamphletId = pamphletId;
    }
}
