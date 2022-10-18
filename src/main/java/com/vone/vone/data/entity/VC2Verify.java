package com.vone.vone.data.entity;

import com.vone.vone.data.dto.CredentialSubject;

public class VC2Verify {
    private String context;
    private String issuerId;
    private CredentialSubject credentialSubject;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public CredentialSubject getCredentialSubject() {
        return credentialSubject;
    }

    public void setCredentialSubject(CredentialSubject credentialSubject) {
        this.credentialSubject = credentialSubject;
    }

    @Override
    public String toString() {
        return "VC2Verfiy{" +
                "context='" + context + '\'' +
                ", issuerId='" + issuerId + '\'' +
                ", credentialSubject=" + credentialSubject +
                '}';
    }
}
