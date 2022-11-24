package com.vone.vone.service;

import com.vone.vone.config.security.JwtTokenProvider;
import com.vone.vone.config.security.TokenInfo;
import com.vone.vone.data.dao.MemberDAO;
import com.vone.vone.data.dto.UserJoinDto;
import com.vone.vone.data.entity.Member;
import com.vone.vone.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private final MemberDAO memberDAO;

    @Transactional
    public TokenInfo login(String memberId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    @Transactional
    public Member join(UserJoinDto userJoinDto) {
        Member member = new Member();
        member.setMemberId(userJoinDto.getMemberId());
        member.setPassword(userJoinDto.getPassword());
        member.getRoles().add(userJoinDto.getRole());
        return memberDAO.join(member);
    }

    @Transactional
    public String info() {
        return memberDAO.info();
    }
}