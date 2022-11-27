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
public class VC2VerifyDto {
    private Long postId;
    private String holder;
    private List<Long> vcIds;
}
