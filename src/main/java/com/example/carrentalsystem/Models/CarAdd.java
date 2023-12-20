package com.example.carrentalsystem.Models;

import java.io.File;

public class CarAdd {
public String name;
public  String categoryName;
public String  seatsNumber;

public  int price;
public File iconimage;

public CarAdd(String name,  int price, File iconimage,String categoryName, String seatsNumber){
    this.name = name;

    this.price = price;
    this.iconimage = iconimage;
    this.categoryName = categoryName;
    this.seatsNumber = seatsNumber;


}
}
