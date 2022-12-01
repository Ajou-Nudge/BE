package com.vone.vone.data.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    private List<String> contextValues = new ArrayList<>();

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

    public List<String> getContextValues() {
        return contextValues;
    }

    public void setContextValues(List<String> contextValues) {
        this.contextValues = contextValues;
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
                ", value1='" + contextValues.get(0) + '\'' +
                ", value2='" + contextValues.get(1) + '\'' +
                ", value3='" + contextValues.get(2) + '\'' +
                ", value4='" + contextValues.get(3) + '\'' +
                ", value5='" + contextValues.get(4) + '\'' +
                ", value6='" + contextValues.get(5) + '\'' +
                ", value7='" + contextValues.get(6) + '\'' +
                ", value8='" + contextValues.get(7) + '\'' +
                '}';
    }
}
