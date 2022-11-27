package com.vone.vone.data.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SubmittedVCResponseDto {
    private List<Long> vcIds;
    private Long postId;
    private String verifier;
    private String title;
    private LocalDateTime date;
    private String status;
}
