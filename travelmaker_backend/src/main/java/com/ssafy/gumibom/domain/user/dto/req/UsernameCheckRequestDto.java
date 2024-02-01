package com.ssafy.gumibom.domain.user.dto.req;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsernameCheckRequestDto {
    private String username;
}
