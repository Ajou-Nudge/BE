package com.vone.vone.service;

import com.vone.vone.config.security.JwtTokenProvider;
import com.vone.vone.config.security.TokenInfo;
import com.vone.vone.data.dao.MemberDAO;
import com.vone.vone.data.dto.UserInfoDto;
import com.vone.vone.data.dto.UserJoinDto;
import com.vone.vone.data.dto.UserLoginRequestDto;
import com.vone.vone.data.entity.Member;
import com.vone.vone.data.repository.UserRepository;
import jnr.a64asm.Mem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserService {

    TokenInfo login(UserLoginRequestDto userLoginRequestDto);

    Member join(UserJoinDto userJoinDto);

    UserInfoDto info();
}