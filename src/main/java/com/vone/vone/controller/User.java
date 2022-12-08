package com.vone.vone.controller;

import com.vone.vone.config.security.TokenInfo;
import com.vone.vone.data.dto.UserInfoDto;
import com.vone.vone.data.dto.UserJoinDto;
import com.vone.vone.data.dto.UserLoginDto;
import com.vone.vone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class User {
    private final UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginDto userLoginDto) {
        TokenInfo tokenInfo = userService.login(userLoginDto);
        return tokenInfo;
    }

    @PostMapping("/join")
    public ResponseEntity<UserJoinDto> join(@RequestBody UserJoinDto userJoinDto) {
        userService.join(userJoinDto);

        return ResponseEntity.status(HttpStatus.OK).body(userJoinDto);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> info() {
        UserInfoDto userInfoDto = userService.info();
        return ResponseEntity.status(HttpStatus.OK).body(userInfoDto);
    }
}