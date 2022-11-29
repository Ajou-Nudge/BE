package com.vone.vone.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VCForSelfIssueDto {
    private String holderId;
    private String title;
    private String issuingAuthority;
    private String IssueDate;
    private String expireDate;
}
