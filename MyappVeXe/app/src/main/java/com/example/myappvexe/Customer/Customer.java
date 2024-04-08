package com.example.myappvexe.Customer;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Customer implements Serializable {
    private long id;
    private String name;
    private String username;
    private String password;
    private String email;

    public Customer(String name) {
        this.name = name;
    }

    public Customer() {
        this.name = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String gender;
    private String dayofbidth;
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDayofbidth() {
        return dayofbidth;
    }

    public void setDayofbidth(String dayofbidth) {
        this.dayofbidth = dayofbidth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString(){
        return name + "\n" + username + "\n" + password + "\n" + email + "\n" + gender + "\n" + dayofbidth + "\n" + phone;
    }
}
