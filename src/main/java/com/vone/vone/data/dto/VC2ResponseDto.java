package com.vone.vone.data.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VC2ResponseDto {
    private Long vcIds;
    private String hash;
}