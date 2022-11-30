package com.vone.vone.service;

import com.vone.vone.config.security.TokenInfo;
import com.vone.vone.data.dto.UserInfoDto;
import com.vone.vone.data.dto.UserJoinDto;
import com.vone.vone.data.dto.UserLoginRequestDto;
import com.vone.vone.data.entity.Member;

import java.util.Optional;

public interface UserService {

    TokenInfo login(UserLoginRequestDto userLoginRequestDto);

    Member join(UserJoinDto userJoinDto);

    UserInfoDto info();
}