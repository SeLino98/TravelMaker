package com.ssafy.gumibom.domain.user.service;

import com.ssafy.gumibom.domain.user.dto.req.LoginReqDto;
import com.ssafy.gumibom.domain.user.dto.req.SignUpReqDto;
import com.ssafy.gumibom.domain.user.dto.res.IsSuccessResDto;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {
    //회원가입
    public IsSuccessResDto saveUser(SignUpReqDto signUpReqDto);

    public IsSuccessResDto loginUser(LoginReqDto loginReqDto);


}
