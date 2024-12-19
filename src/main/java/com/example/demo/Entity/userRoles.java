package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class userRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usr;

    public userRoles() {
    }

    public userRoles(String role, User usr) {
        this.role = role;
        this.usr = usr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    @Override
    public String toString() {
        return "userRoles [id=" + id + ", role=" + role + ", usr=" + usr + "]";
    }
}
