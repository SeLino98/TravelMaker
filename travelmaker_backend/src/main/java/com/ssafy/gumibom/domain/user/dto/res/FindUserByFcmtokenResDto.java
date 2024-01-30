package com.ssafy.gumibom.domain.user.dto.res;

import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class UserResDto {
    private Long id;

    private String loginId;
    private String password;
    private String email;
    private String nickname;
    private boolean gender;
    private String birth;
    private String phone;
    private String imgURL;
    private double belief;
    private String town;
    private Nation nation;
    private List<Category> categories;
}
