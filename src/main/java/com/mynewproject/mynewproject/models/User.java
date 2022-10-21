package com.mynewproject.mynewproject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_details")
public class User {

    protected User(){

    }
    @Id
    @GeneratedValue
    @JsonProperty("user_id")
    private int id;
    @Size(min=4,message="Name should be at least 5 characters.")
    private String name;
    private int age;
    @OneToMany(mappedBy = "user")
  @JsonIgnore
    private List<Post> posts;
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
