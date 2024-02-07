package com.ssafy.gumibom.domain.user.sms;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sms-certification")
public class SmsController {
    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<?> sendSms(@RequestBody SmsRequestDto requestDto) throws Exception {
        try {
            smsService.sendSms(requestDto);
            return ResponseEntity.ok("문자 발송이 정상적으로 완료되었습니다.");
        } catch (CustomExceptions.Exception e) {
            return handleApiException(e, HttpStatus.BAD_REQUEST);
        }
    }

    //인증번호 확인
    @PostMapping("/confirm")
    public ResponseEntity<?> SmsVerification(@RequestBody SmsRequestDto requestDto) throws Exception{
        try {
            smsService.verifySms(requestDto);
            return ResponseEntity.ok("문자 인증이 성공했습니다.");
        } catch (CustomExceptions.Exception e) {
            return handleApiException(e, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<DefaultRes<String>> handleApiException(CustomExceptions.Exception e, HttpStatus status) {
        DefaultRes<String> response = DefaultRes.res(status.value(), e.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
