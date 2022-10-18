package com.vone.vone.data.dto;

public class ContextDto {
    private String context;
    private CredentialSubject credentialSubject;

    public ContextDto(){}

    public ContextDto(String context, CredentialSubject credentialSubject){
        this.context=context;
        this.credentialSubject=credentialSubject;
    }

    public String getContext(){
        return context;
    }
    public void setContext(String context){
        this.context = context;
    }

    public CredentialSubject getCredentialSubject(){
        return credentialSubject;
    }
    public void setCredentialSubject(CredentialSubject credentialSubject){
        this.credentialSubject=credentialSubject;
    }

    public String toString(){
        return "MemberDto{"+
                "name= '" + context + "\'" +
                ", email= '" + credentialSubject + "\'" +
                "}";
    }
}
