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
public class ContextDto {
    private String context;
    private List<String> credentialSubject;
}
