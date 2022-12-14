package com.vone.vone.data.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table( name="VC" )
public class VC {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String context;
    @Column(nullable = false)
    private String issuer;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> credentialSubject;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Map<String, String> getCredentialSubject() {
        return credentialSubject;
    }

    public void setCredentialSubject(Map<String, String> credentialSubject) {
        this.credentialSubject = credentialSubject;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "VC{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", issuer='" + issuer + '\'' +
                ", value1='" + credentialSubject.get(0) + '\'' +
                ", value2='" + credentialSubject.get(1) + '\'' +
                ", value3='" + credentialSubject.get(2) + '\'' +
                ", value4='" + credentialSubject.get(3) + '\'' +
                ", value5='" + credentialSubject.get(4) + '\'' +
                ", value6='" + credentialSubject.get(5) + '\'' +
                ", value7='" + credentialSubject.get(6) + '\'' +
                ", value8='" + credentialSubject.get(7) + '\'' +
                '}';
    }
}