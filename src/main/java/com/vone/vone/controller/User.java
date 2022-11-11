package com.vone.vone.controller;

import javax.validation.Valid;

import com.vone.vone.data.dto.RegisterDto;
import com.vone.vone.data.dto.UserDto;
import com.vone.vone.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import springfox.documentation.spring.web.json.Json;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class User {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(RegisterDto registerDto) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterDto> signup(@Valid RegisterDto registerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.OK).body(registerDto);
        }

        if (!registerDto.getPassword1().equals(registerDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registerDto);
        }

        try {
            userService.create(registerDto.getUsername(), registerDto.getEmail(), registerDto.getPassword1());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return ResponseEntity.status(HttpStatus.OK).body(registerDto);
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(registerDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(registerDto);
    }
}