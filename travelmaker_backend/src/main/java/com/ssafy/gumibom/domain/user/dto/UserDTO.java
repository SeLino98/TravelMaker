package com.ssafy.gumibom.domain.user.dto;

import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.management.relation.Role;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private Long id;
    @NotEmpty(message = "아이디는 필수 입력값 입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 14, message = "비밀번호는 8자 이상, 14자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    private boolean gender;
    private String birth;
    private String phoneNum;
    private String imgURL;
    private String town;
    private Nation nation;
    private List<Category> categories;

    private String uuid;
    private Role role;


}
