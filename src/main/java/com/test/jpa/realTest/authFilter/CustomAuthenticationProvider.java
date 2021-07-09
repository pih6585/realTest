package com.test.jpa.realTest.authFilter;

import com.test.jpa.realTest.entity.Member;
import com.test.jpa.realTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(BCryptPasswordEncoder bCryptPasswordEncoder, MemberService memberService) {
        this.passwordEncoder = bCryptPasswordEncoder;
        this.memberService = memberService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userEmail = token.getName();
        System.out.println(token.getName()+"============================");
        String userPw = (String) token.getCredentials();
        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회

        UserDetails userDetails = memberService.loadUserByUsername(userEmail);
        if (!passwordEncoder.matches(userPw, userDetails.getPassword())) {
            throw new IllegalStateException("패스워드 불일치");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userPw, null);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
