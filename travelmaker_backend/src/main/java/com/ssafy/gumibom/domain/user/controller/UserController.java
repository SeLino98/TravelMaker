package com.ssafy.gumibom.domain.user.controller;

import com.ssafy.gumibom.domain.user.dto.AccountModifyRequestDTO;
import com.ssafy.gumibom.domain.user.dto.JwtToken;
import com.ssafy.gumibom.domain.user.dto.LoginRequestDTO;
import com.ssafy.gumibom.domain.user.dto.SignupRequestDto;
import com.ssafy.gumibom.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.io.IOException;

@Tag(name = "User", description = "회원 관련 api")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<JwtToken> loginSuccess(@RequestBody LoginRequestDTO requestDTO) {
        JwtToken token = userService.login(requestDTO.getLoginId(), requestDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "회원가입")
    @PostMapping(value = "/join", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public Long signup(@RequestPart SignupRequestDto requestDto, @RequestPart MultipartFile image) throws IOException {
        return userService.signup(requestDto, image);
    }

    // 전화번호 중복 체크
    @Operation(summary = "전화번호 중복 체크")
    @GetMapping("/join/check/phone-number-exists/{phonenum}")
    public ResponseEntity<?> checkPhoneNumDuplicate(@PathVariable String phonenum) {
        return ResponseEntity.ok(userService.checkPhoneNumExists(phonenum));
    }

    // 닉네임 중복 체크
    @Operation(summary = "닉네임 중복 체크")
    @GetMapping("/join/check/nickname-exists/{nickname}")
    public ResponseEntity<?> checkNickNameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkNickNameExists(nickname));
    }

    // Login ID 중복 체크
    @Operation(summary = "아이디 중복 체크")
    @GetMapping("/join/check/id-exists/{loginID}")
    public ResponseEntity<?> checkLoginIDDuplicate(@PathVariable String loginID) {
        return ResponseEntity.ok(userService.checkLoginIDExists(loginID));
    }

    @Operation(summary = "마이페이지")
    @GetMapping("/mypage")
    public ResponseEntity<?> mypage(@RequestParam String userLoginId) {
        return ResponseEntity.ok(userService.inquiryMyPage(userLoginId));
    }

    @Operation(summary = "fcm 토큰 갱신")
    @PutMapping("/user/renewal-fcm-token")
    public ResponseEntity<?> renewalFCM(@RequestBody AccountModifyRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.updateFCMById(requestDTO));
    }
}
