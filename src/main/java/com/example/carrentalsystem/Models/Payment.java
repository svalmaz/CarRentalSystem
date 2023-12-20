package com.example.carrentalsystem.Models;

public class Payment {
    public String name;
    public String cardNumber;
    public String cvv;
    public int userId;

    public String expiration;
    public Payment(String name, String cvv, String cardNumber, String expiration, int userId){
        this.name= name;
        this.expiration = expiration;
        this.cvv = cvv;
        this.cardNumber = cardNumber;
        this.userId = userId;
    }


}
