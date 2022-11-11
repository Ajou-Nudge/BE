package com.vone.vone.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String name;
    private String email;
    private String password;
    private Long id;
}
