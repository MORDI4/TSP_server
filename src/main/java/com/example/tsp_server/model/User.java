package com.example.tsp_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_Id;
    private String name;
    private String password;
    private Integer languageId;
    private String login;

    // Getters
    public Integer getUserId() {
        return user_Id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public String getLogin() {
        return login;
    }

    // Setters
    public void setUserId(Integer userId) {
        this.user_Id = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
