package com.ssafy.gumibom.domain.user.dto.req;

import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReqDto {
    private String loginId;
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String birth;

    @NotBlank
    private String phone;

    @NotBlank
    private boolean gender;


    private List<Category> categories;

    @NotBlank
    private Nation nation;
}
