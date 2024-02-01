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

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;
    private String nickname;
    private String email;
    private String location;
    private Gender gender;
    private String birth;
    private String phone;
    private String profileImgURL;
    private Nation nation;
    private List<Category> categories;
    private Role role;

    @Builder
    public User toEntity(){
        return User.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .nation(nation)
                .birth(birth)
                .password(password)
                .phone(phone)
                .role(role)
                .imgURL(profileImgURL)
                .categories(categories)
                .build();
    }
}
