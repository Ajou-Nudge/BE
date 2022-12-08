package com.vone.vone.data.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

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

    private List<String> required;

    private String url;

    private String verifier;

}
