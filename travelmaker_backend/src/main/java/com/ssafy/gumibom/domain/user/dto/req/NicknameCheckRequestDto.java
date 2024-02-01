package com.ssafy.gumibom.domain.user.dto.req;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NicknameCheckRequestDto {
    private String nickname;
}
