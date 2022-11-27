package com.vone.vone.data.entity;

import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="SubmittedVC" )
public class SubmittedVC {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;
    @ElementCollection
    @Builder.Default
    private List<Long> vcIds = new ArrayList<>();
    @Column(nullable = false)
    private String holderId;
    @Column(nullable = false)
    private String IssuerId;
    @Column(nullable = false)
    private String verifierId;

    @Column(nullable = false)
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getVcIds() {
        return vcIds;
    }

    public void setVcIds(List<Long> vcIds) {
        this.vcIds = vcIds;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getIssuerId() {
        return IssuerId;
    }

    public void setIssuerId(String issuerId) {
        IssuerId = issuerId;
    }

    public String getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(String verifierId) {
        this.verifierId = verifierId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "SubmittedVCs{" +
                "id=" + id +
                ", vcId=" + vcIds +
                ", holderId='" + holderId + '\'' +
                ", IssuerId='" + IssuerId + '\'' +
                ", verifierId='" + verifierId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
