package com.ssafy.gumibom.domain.user.dto.req;

import com.ssafy.gumibom.domain.user.entity.Gender;
import com.ssafy.gumibom.domain.user.entity.Role;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String town;
    private Gender gender;
    private String birth;
    private String phone;
    private String profileImgURL;
    private Nation nation;
    private List<Category> categories;
    private Role role;

}
