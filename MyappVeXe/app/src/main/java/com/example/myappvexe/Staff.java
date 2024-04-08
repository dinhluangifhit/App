package com.example.myappvexe;

import java.io.Serializable;

public class Staff implements Serializable {
    private long id;
    private String name;
    private String username;
    private String password;
    private String email;
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
    public void setName(String name){
        this.name = name;
     }

     public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

     public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public  String  getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name + "\n" + username + "\n" + password + "\n" + email + "\n" + phone;

    }

}
