package com.example.login_study.demo.controller;

import com.example.login_study.demo.dto.AuthDto;
import com.example.login_study.demo.dto.LoginDto;
import com.example.login_study.demo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AuthDto authDto){
        Long createId = memberService.signUp(authDto);

        return ResponseEntity.ok(createId+"번 계정 생성 완료!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request){

        Long memberId = memberService.login(loginDto);

        HttpSession session = request.getSession();
        session.setAttribute("memberId",memberId);

        return ResponseEntity.ok("로그인 성공");
    }

}
