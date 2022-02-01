package com.revature.models;

import java.io.Serializable;

public class Person implements Serializable {

    private int id;
    private Type type;
    private String first;
    private String last;
    private String email;
    private String username;
    private String password;

    public Person() {
    }

    public Person(Type type, String first, String last, String email, String username, String password) {
        this.type = type;
        this.first = first;
        this.last = last;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Person(int id, Type type, String first, String last, String email, String username, String password) {
        this.id = id;
        this.type = type;
        this.first = first;
        this.last = last;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", type=" + type +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

