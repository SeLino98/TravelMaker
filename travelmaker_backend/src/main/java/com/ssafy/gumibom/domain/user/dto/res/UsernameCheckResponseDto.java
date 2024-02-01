package com.ssafy.gumibom.domain.user.dto.res;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsernameCheckResponseDto {
    private boolean isAvailable;
    private String message;
}
