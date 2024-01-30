package com.ssafy.gumibom.domain.user.dto.req;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsReqDto {
    private String phone;
}
