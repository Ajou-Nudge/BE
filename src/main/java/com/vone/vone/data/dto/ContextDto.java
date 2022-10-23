package com.vone.vone.data.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ContextDto {
    private String context;
    private CredentialSubject credentialSubject;
}
