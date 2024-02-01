package com.ssafy.gumibom.domain.user.dto.req;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    private String username;
    private String password;
}
