package com.ssafy.gumibom.global.base;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BooleanResponseDto extends BaseResponseDto {

    private final Boolean booleanValue;
    public BooleanResponseDto(Boolean isSuccess, String message, String stringValue, Boolean booleanValue) {
        super(isSuccess, message);
        this.booleanValue = booleanValue;
    }
}
