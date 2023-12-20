package com.example.carrentalsystem.Models;

public class UserReq {
    public String username;
    public String name;
    public String pass;
    public UserReq(String username, String pass,String name){
        this.username = username;
        this.pass = pass;
        this.name = name;
    }
}
