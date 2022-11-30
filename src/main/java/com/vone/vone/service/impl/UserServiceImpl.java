package com.vone.vone.service.impl;

import com.vone.vone.config.security.JwtTokenProvider;
import com.vone.vone.config.security.TokenInfo;
import com.vone.vone.data.dao.MemberDAO;
import com.vone.vone.data.dto.UserInfoDto;
import com.vone.vone.data.dto.UserJoinDto;
import com.vone.vone.data.dto.UserLoginRequestDto;
import com.vone.vone.data.entity.Member;
import com.vone.vone.data.repository.UserRepository;
import com.vone.vone.service.UserService;
import jnr.a64asm.Mem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDAO memberDAO;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl (PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider, MemberDAO memberDAO, UserRepository userRepository) {
        this.passwordEncoder =passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberDAO = memberDAO;
        this.userRepository = userRepository;
    }

    @Override
    public TokenInfo login(UserLoginRequestDto userLoginRequestDto) {

        Member member = userRepository
                .findByMemberId(userLoginRequestDto.getMemberId())
                .orElseThrow(() -> new UsernameNotFoundException("아이디 혹은 비밀번호를 확인하세요."));

        boolean matches = passwordEncoder.matches(userLoginRequestDto.getPassword(), member.getPassword());
        if (!matches) throw new BadCredentialsException("아이디 혹은 비밀번호를 확인하세요.");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    @Override
    public Member join(UserJoinDto userJoinDto) {

        boolean isExist = userRepository.existsByMemberId(userJoinDto.getMemberId());
        if (isExist) throw new AlreadyBuiltException("이미 존재하는 아이디입니다.");

        Member member = new Member();
        member.setMemberId(userJoinDto.getMemberId());
        member.setPassword(passwordEncoder.encode(userJoinDto.getPassword()));
        member.getRoles().add(userJoinDto.getRole());
        return memberDAO.join(member);
    }

    @Override
    public UserInfoDto info() {
        return memberDAO.info();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMemberId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}