package com.ssafy.gumibom.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    //로그인 페이지 Security에서 제공하는
    @PostMapping("/login")
    public String loginPage(){
        return "/login";
    }
}
