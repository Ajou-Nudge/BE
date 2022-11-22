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
public class PostResponseDto {

    private Long id;

    private String title;

    private String expired;

    private List<String> required;

    private String url;

    private String verifierId;
}
