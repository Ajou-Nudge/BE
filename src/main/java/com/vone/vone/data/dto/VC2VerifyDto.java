package com.vone.vone.data.dto;

import java.util.List;

public class VC2VerifyDto {
    private Long postId;
    private String holder;
    private List<Long> vcIds;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long podtd) {
        this.postId = podtd;
    }

    public List<Long> getVcIds() {
        return vcIds;
    }

    public void setVcIds(List<Long> vcIds) {
        this.vcIds = vcIds;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "VC2VerifyDto{" +
                "postId=" + postId +
                ", holder='" + holder + '\'' +
                ", vcIds=" + vcIds +
                '}';
    }
}
