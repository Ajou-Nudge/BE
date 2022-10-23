package com.vone.vone.data.dto;

import lombok.*;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VCDto {
    private String context;
    private String issuer;
    private CredentialSubject credentialSubject;

}
