package com.vone.vone.data.dto;

public class VC2IssueDto {
    private String holderId;
    private VCDto vc;

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public VCDto getVc() {
        return vc;
    }

    public void setVc(VCDto vc) {
        this.vc = vc;
    }

    @Override
    public String toString() {
        return "VC2Issue{" +
                "holderId='" + holderId + '\'' +
                ", vc=" + vc +
                '}';
    }
}
