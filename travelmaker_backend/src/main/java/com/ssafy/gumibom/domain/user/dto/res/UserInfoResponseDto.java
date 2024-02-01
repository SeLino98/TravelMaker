package com.ssafy.gumibom.domain.user.dto.res;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.global.common.Category;
import com.ssafy.gumibom.global.common.Nation;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponseDto {
    private String nickname;
    private String imgURL;
    private User.Gender gender;
    private String birth;
    private double belief;
    private Nation nation;
    private List<Category> categories;
}
