package com.vone.vone.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VC2IssueDto {
    private String holder;
    private VCDto vc;
}
