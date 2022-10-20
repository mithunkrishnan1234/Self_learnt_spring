package com.mynewproject.mynewproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    @Size(min=1)
    private String message;
    @ManyToOne(fetch=FetchType.LAZY)
   @JsonIgnore
    private User user;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}

