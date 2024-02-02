package com.ssafy.gumibom.domain.user.dto.res;

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
public class SignupResponseDto {
    private Long id;
    private String username;
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

//    static public SignupResponseDto toDto(User user){
//        return SignupResponseDto.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .nickname(user.getNickname())
//                .email(user.getEmail())
//                .town(user.getTown()) // 가정: User 클래스의 town 필드가 location에 해당함
//                .gender(user.getGender())
//                .birth(user.getBirth())
//                .phone(user.getPhone())
//                .profileImgURL(user.getImgURL())
//                .nation(user.getNation())
//                .categories(user.getCategories())
//                .role(user.getRole())
//                .build();
//    }

//    public User toEntity(){
//        return User.builder()
//                .id(id)
//                .username(username)
//                .nickname(nickname)
//                .email(email)
//                .town(town) // 가정: User 클래스의 town 필드가 location에 해당함
//                .gender(gender)
//                .birth(birth)
//                .password(null) // 비밀번호는 DTO에 포함되지 않았으므로 null 또는 적절한 값 설정
//                .phone(phone)
//                .imgURL(profileImgURL)
//                .nation(nation)
//                .categories(categories)
//                .role(role)
//                .build();
//    }
}
