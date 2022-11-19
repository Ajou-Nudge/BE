package com.vone.vone.data.dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String memberId;
    private String password;
}
