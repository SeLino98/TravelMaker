package com.ssafy.gumibom.domain.user.dto.req;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginReqDto {
    private String loginId;
    private String password;
}
