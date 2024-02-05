package com.ssafy.gumibom.domain.user.service;

import com.ssafy.gumibom.domain.user.dto.JwtToken;
import com.ssafy.gumibom.domain.user.dto.MyPageResponseDTO;
import com.ssafy.gumibom.domain.user.dto.SignupRequestDto;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    // 로그인
    @Transactional
    public JwtToken login(String loginId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, password);

        System.out.println(authenticationToken);

        // 검증
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println(authentication);
        // jwt 토큰 생성
        JwtToken token = jwtTokenProvider.generateToken(authentication);

        return token;
    }


    //회원가입
    @Transactional
    public Long signup(SignupRequestDto requestDto) {

        boolean check = checkPhoneNumExists(requestDto.getPhone());
        if (check) throw new IllegalArgumentException("이미 가입된 전화번호입니다.");

        String encPwd = encoder.encode(requestDto.getPassword());
        userRepository.save(requestDto.toEntity(encPwd));
        User user = userRepository.findByUsername(requestDto.getUsername());

        if (user != null) {
            return user.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

//        return user.getId();
    }


    // 전화번호 중복 가입 체크
    @Transactional
    public boolean checkPhoneNumExists(String phoneNum) {
        return userRepository.existUsersByPhoneNum(phoneNum);
    }

    // mypage
    @Transactional
    public MyPageResponseDTO inquiryMyPage(String userLoginId) {
//        return userRepository.findByUsername(userLoginId);
        User user = userRepository.findByUsername(userLoginId);
        return MyPageResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .profileImgURL(user.getProfileImgURL())
                .trust(user.getTrust())
                .town(user.getTown())
                .nation(user.getNation())
                .categories(user.getCategories())
                .build();
    }



    /*


    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByUsername(user.getUsername());
        if(findUser != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    */

}
