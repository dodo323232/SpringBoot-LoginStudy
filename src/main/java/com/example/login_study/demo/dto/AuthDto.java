package com.example.login_study.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthDto {
    private Long memberId;
    private String email;
    private String password;
    private String nickname;
}
