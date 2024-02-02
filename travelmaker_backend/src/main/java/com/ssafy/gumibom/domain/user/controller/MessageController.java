//package com.ssafy.gumibom.domain.user.controller;
//
//import com.ssafy.gumibom.domain.user.service.SmsService;
//import net.nurigo.sdk.NurigoApp;
//import net.nurigo.sdk.message.model.Message;
//import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
//import net.nurigo.sdk.message.response.SingleMessageSentResponse;
//import net.nurigo.sdk.message.service.DefaultMessageService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//
//
//import java.util.Random;
//
//@Controller
//public class MessageController {
//    private final DefaultMessageService messageService;
//
//    @Value("${coolsms.apikey}")
//    private String apiKey;
//
//    @Value("${coolsms.apisecret}")
//    private String apiSecret;
//
//    @Value("${coolsms.fromnumber}")
//    private String fromNumber;
//
//    public MessageController() {
//        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("apikey = " + this.apiKey);
//        System.out.println("apiSecret = " + this.apiSecret);
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//        System.out.println("==========================================");
//
//        this.messageService = NurigoApp.INSTANCE.initialize(this.apiKey, this.apiSecret, "https://api.coolsms.co.kr");
//    }
//
//    @PostMapping("/send-one")
//    public SingleMessageSentResponse sendOne() {
//        Message message = new Message();
//
//        String randomNum = this.createRandomNumber();
//
//        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
//        message.setFrom(fromNumber);
//        message.setTo("수신번호 입력");
//        message.setText("인증번호는 " + randomNum + "입니다.");
//
//        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        System.out.println(response);
//
//        return response;
//    }
//
//    // 랜덤한 인증 번호 생성
//    private String createRandomNumber() {
//        Random rand = new Random();
//        String randomNum = "";
//        for (int i = 0; i < 4; i++) {
//            String random = Integer.toString(rand.nextInt(10));
//            randomNum += random;
//        }
//
//        return randomNum;
//    }
//}
