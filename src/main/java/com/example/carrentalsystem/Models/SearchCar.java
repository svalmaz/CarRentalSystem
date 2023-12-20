package com.example.carrentalsystem.Models;

public class SearchCar {
    public String name;
    public int startPrice;
    public int endPrice;
    public SearchCar(String name, int startPrice, int endPrice){
        this.name = name;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }
}
