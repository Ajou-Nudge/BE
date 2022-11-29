package com.vone.vone.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name="SelfIssued" )
public class SelfIssued {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String holderId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String issuingAuthority;
    @Column(nullable = false)
    private String IssueDate;
    @Column(nullable = false)
    private String expireDate;
    @Column(nullable = false)
    private String fileName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
