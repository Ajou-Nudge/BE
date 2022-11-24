package com.vone.vone.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserJoinDto {
    private String memberId;
    private String password;
    private String role;
}
