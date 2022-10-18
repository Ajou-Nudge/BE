package com.vone.vone.data.dto;

public class VC {
    private String context;
    private String issuer;
    private CredentialSubject credentialSubject;

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

    public CredentialSubject getCredentialSubject() {
        return credentialSubject;
    }

    public void setCredentialSubject(CredentialSubject credentialSubject) {
        this.credentialSubject = credentialSubject;
    }

    @Override
    public String toString() {
        return "VC{" +
                "context='" + context + '\'' +
                ", issuer='" + issuer + '\'' +
                ", credentialSubject=" + credentialSubject +
                '}';
    }
}
