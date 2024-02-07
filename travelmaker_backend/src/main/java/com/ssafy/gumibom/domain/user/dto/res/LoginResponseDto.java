package com.ssafy.gumibom.domain.user.dto.res;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private Boolean isSuccess;
    private String message;
    private String jwtToken;
}
