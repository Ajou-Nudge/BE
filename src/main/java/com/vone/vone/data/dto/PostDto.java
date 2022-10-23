package com.vone.vone.data.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostDto {
    private String title;

    private String expired;

    private String required;

    private String url;

    private String verifierId;

}
