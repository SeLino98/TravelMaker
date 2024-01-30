package com.ssafy.gumibom.domain.user.service;

import com.ssafy.gumibom.domain.user.dto.req.LoginReqDto;
import com.ssafy.gumibom.domain.user.dto.req.SignUpReqDto;
import com.ssafy.gumibom.domain.user.dto.res.IsSuccessResDto;
import com.ssafy.gumibom.domain.user.entity.User;
import com.ssafy.gumibom.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public IsSuccessResDto saveUser(SignUpReqDto signUpReqDto) {
        try {
            User user = convertToUser(signUpReqDto);

            user.setPassword(encoder.encode(signUpReqDto.getPassword()));

            userRepository.saveUser(user);

            // 회원가입 성공 시
            IsSuccessResDto isSuccessResDto = IsSuccessResDto.builder()
                    .isSuccess(true)
                    .message("회원가입에 성공했습니다.")
                    .build();

            return isSuccessResDto;
        } catch (Exception e) {
            // 회원가입 실패 시
            IsSuccessResDto isSuccessResDto = IsSuccessResDto.builder()
                    .isSuccess(false)
                    .message("회원가입에 실패했습니다. " + e.getMessage())
                    .build();

            return isSuccessResDto;
        }
    }

    @Override
    public IsSuccessResDto loginUser(LoginReqDto loginReqDto) {
        return null;
    }

    private User convertToUser(SignUpReqDto signUpReqDto) {
        // SignUpReqDto를 User 엔티티로 변환하는 메서드
        // 실제 프로젝트에서는 이 부분을 자세히 디자인하여 사용자 입력값을 엔티티로 매핑하는 방식을 선택할 수 있습니다.
        return new User(
                signUpReqDto.getLoginId(),
                signUpReqDto.getPassword(),
                signUpReqDto.getNickname(),
                signUpReqDto.getBirth(),
                signUpReqDto.getPhone(),
                signUpReqDto.isGender(),
                signUpReqDto.getCategories(),
                signUpReqDto.getNation()
        );
    }



}
