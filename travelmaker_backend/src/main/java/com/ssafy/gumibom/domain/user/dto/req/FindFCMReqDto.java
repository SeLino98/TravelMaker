package com.ssafy.gumibom.domain.user.dto.req;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindFCMReqDto {
    private String fcmtoken;
}
