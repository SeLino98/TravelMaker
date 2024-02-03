package com.ssafy.gumibom.global.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponseDto {

    private final Boolean isSuccess;
    private final String message;
}
