package com.ssafy.gumibom.domain.user.dto.res;

import lombok.*;

@Getter
@Builder
public class FindIdResDto {
    private boolean isSuccess;
    private String message;
    private String loginId;
}
