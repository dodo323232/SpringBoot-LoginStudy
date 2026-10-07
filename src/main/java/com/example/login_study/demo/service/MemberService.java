package com.example.login_study.demo.service;

import com.example.login_study.demo.dto.LoginDto;
import com.example.login_study.demo.mapper.MemberMapper;
import com.example.login_study.demo.dto.AuthDto;
import com.example.login_study.demo.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public Long signUp(AuthDto authDto){
        if(memberMapper.findByEmail(authDto.getEmail()) != null){
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
        }
        if (memberMapper.findByNickname(authDto.getNickname()) != null){
            throw new IllegalArgumentException("존재하는 닉네임입니다.");
        }

        String encodedPassword = passwordEncoder.encode(authDto.getPassword());

        Member member = Member.builder()
                .email(authDto.getEmail())
                .password(encodedPassword)
                .nickname(authDto.getNickname())
                .build();
        memberMapper.save(member);
        return member.getMemberId();
    }

    public Long login(LoginDto loginDto){

        Member member = memberMapper.findByEmail(loginDto.getEmail());

        if(member == null){
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다");
        }

        boolean isPasswordMatched = passwordEncoder.matches(loginDto.getPassword(), member.getPassword());

        if(!isPasswordMatched){
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다");
        }

        return member.getMemberId();
    }
}
