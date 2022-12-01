package com.vone.vone.data.entity;

import com.vone.vone.data.dto.CredentialSubject;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="Context" )
public class Context {
    @Id
    private String context;
    @ElementCollection
    @Builder.Default
    private List<String> contextValues = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
}
