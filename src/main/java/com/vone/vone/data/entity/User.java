package com.vone.vone.data.entity;

public class User {
    private String name;
    private String email;
    private String password;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password=password;
    }

    @Override
    public String toString(){
        return "MemberDto{"+
                "name= '" + name + "\'" +
                ", email= '" + email + "\'" +
                "}";
    }
}
