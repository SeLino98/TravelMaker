package com.ssafy.gumibom.domain.user.dto.req;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindPwReqDto {
    private String loginId;
    private String phone;
}
