package com.vone.vone.data.dto;

public class PostResponseDto {

    private Long id;

    private String title;

    private String expired;

    private String required;

    private String url;

    private String verifierId;

    public PostResponseDto() {}

    public PostResponseDto(Long id,String title, String expired, String required, String url, String verifierId){
        this.id=id;
        this.title=title;
        this.expired=expired;
        this.required=required;
        this.url=url;
        this.verifierId=verifierId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
