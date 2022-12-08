package com.vone.vone.service;

import com.vone.vone.config.security.TokenInfo;
import com.vone.vone.data.dto.UserInfoDto;
import com.vone.vone.data.dto.UserJoinDto;
import com.vone.vone.data.dto.UserLoginDto;
import com.vone.vone.data.entity.Member;

public interface UserService {

    TokenInfo login(UserLoginDto userLoginDto);

    Member join(UserJoinDto userJoinDto);

    UserInfoDto info();
}