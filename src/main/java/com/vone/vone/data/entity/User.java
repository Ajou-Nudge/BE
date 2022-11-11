package com.vone.vone.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){ this.email=email; }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){ this.password=password; }

    @Override
    public String toString(){
        return "UserDto{"+
                "name= '" + name + "\'" +
                ", email= '" + email + "\'" +
                "}";
    }
}
