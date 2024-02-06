package com.ssafy.gumibom.domain.user.controller;

import com.ssafy.gumibom.domain.user.dto.JwtToken;
import com.ssafy.gumibom.domain.user.dto.LoginRequestDTO;
import com.ssafy.gumibom.domain.user.dto.SignupRequestDto;
import com.ssafy.gumibom.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> loginSuccess(@RequestBody LoginRequestDTO requestDTO) {
        JwtToken token = userService.login(requestDTO.getLoginId(), requestDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/join")
    public Long signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    // 전화번호 중복 체크
    @GetMapping("/join/check/{phonenum}/exists")
    public ResponseEntity<?> checkPhoneNumDuplicate(@PathVariable String phonenum) {
        return ResponseEntity.ok(userService.checkPhoneNumExists(phonenum));
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> mypage(@RequestParam String userLoginId) {
        return ResponseEntity.ok(userService.inquiryMyPage(userLoginId));
    }
}
