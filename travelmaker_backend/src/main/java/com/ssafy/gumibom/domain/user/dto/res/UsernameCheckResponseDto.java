package com.ssafy.gumibom.domain.user.dto.res;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsernameCheckResponseDto {
    private Boolean isAvailable;
    private String message;
}
