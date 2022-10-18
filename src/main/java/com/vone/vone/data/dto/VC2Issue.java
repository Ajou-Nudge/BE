package com.vone.vone.data.dto;

public class VC2Issue {
    private String holderId;
    private VC vc;

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public VC getVc() {
        return vc;
    }

    public void setVc(VC vc) {
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
