package com.ssafy.gumibom.domain.user.dto.res;

import lombok.*;

@Getter
@Builder
public class CommonResDto {
    /*
    회원가입 성공여부
    회원가입 문자발송 여부
    로그인 성공여부
    아이디찾기 대상 유무
    비밀번호 변경 대상 유무
    비밀번호 변경 성공여부
    * */

    private boolean isSuccess;
    private String message;
}
