package com.vone.vone.data.dto;

import java.time.LocalDateTime;

public class SubmittedVCResponseDto {
    private Long vcId;
    private Long postId;
    private String verifier;
    private String title;
    private LocalDateTime date;
    private String status;

    public Long getVcId() {
        return vcId;
    }

    public void setVcId(Long vcId) {
        this.vcId = vcId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SubmittedVCResponseDto{" +
                "vcId=" + vcId +
                ", postId=" + postId +
                ", verifier='" + verifier + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
