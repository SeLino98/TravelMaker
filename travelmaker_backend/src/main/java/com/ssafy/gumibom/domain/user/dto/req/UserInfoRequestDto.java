package com.ssafy.gumibom.domain.user.dto.req;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoRequestDto {
    private Long userId;
    private String fcmtoken;
}
