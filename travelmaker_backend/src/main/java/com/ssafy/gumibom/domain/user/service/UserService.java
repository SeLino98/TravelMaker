package com.ssafy.gumibom.domain.user.service;

import com.ssafy.gumibom.domain.user.dto.req.LoginRequestDto;
import com.ssafy.gumibom.domain.user.dto.req.SignupRequestDto;
import com.ssafy.gumibom.domain.user.dto.res.LoginResponseDto;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerUser(SignupRequestDto signupRequestDto){

        // 비밀번호 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(signupRequestDto.getPassword());

        // User 객체 생성
        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .password(encodedPassword) // 암호화된 비밀번호 사용
                .email(signupRequestDto.getEmail())
                .gender(signupRequestDto.getGender())
                .phone(signupRequestDto.getPhone())
                .nation(signupRequestDto.getNation())
                .categories(signupRequestDto.getCategories())
                .imgURL(signupRequestDto.getProfileImgURL())
                .build();

        userRepository.save(user);
    }


}
