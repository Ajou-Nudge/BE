package com.vone.vone.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostDto {
    private String title;

    private String expired;

    private String required;

    private String url;

    private String verifierId;

    public PostDto(String title, String expired, String required, String url, String verifierId){
        this.title=title;
        this.expired=expired;
        this.required=required;
        this.url=url;
        this.verifierId=verifierId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(String verifierId) {
        this.verifierId = verifierId;
    }
}
