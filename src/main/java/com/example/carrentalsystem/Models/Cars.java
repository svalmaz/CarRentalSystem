package com.example.carrentalsystem.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Cars {
    private int id;
    private String name;
    private String image;
    private int price;
    private boolean isAvailable;
    private String category;
    private int seats;


    public int getId() {
        return id;
    }
    public String getName() {return name;}
    public String getImage() {
        return image;
    }
    public int getPrice() {
        return price;
    }
    public int getSeats() {
        return seats;
    }
    public String getCategory(){return category;}
    public boolean getAvailable() { return isAvailable; }

    public void setId(int firstName) {id = firstName;}
    public void setName(String firstName) {name = firstName;}
    public void setImage(String firstName) {
        image= firstName;
    }
    public void setPrice(int firstName) {
        price = firstName;
    }
    public void setAvailable(boolean firstName) { isAvailable = firstName; }
    public static ObservableList<Cars> getInitialList() {
        Cars p1 = new Cars(1, "Audi", 100, "asd", true, "1", 1);
        Cars p2 = new Cars(2, "Audi1", 100, "as1d", false,"1", 1);
        Cars p3 = new Cars(3, "Audi2", 100, "as2d", false,"1", 1);

        return FXCollections.observableArrayList(p1, p2, p3);
    }

    public static TableColumn<Cars, String> getNameCol(){
        TableColumn<Cars, String> fNameCol = new TableColumn<>("Name");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        return fNameCol;
    }

    public static TableColumn<Cars, String> getImageCol(){
        TableColumn<Cars, String> lNameCol = new TableColumn<>("Image");
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        return lNameCol;
    }

    public static TableColumn<Cars, Integer> getIdCol(){
        TableColumn<Cars, Integer> originCol = new TableColumn<>("Id");
        originCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return originCol;
    }
    public static TableColumn<Cars, Integer> getPriceCol(){
        TableColumn<Cars, Integer> originCol = new TableColumn<>("Price");
        originCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        return originCol;
    }
    public static TableColumn<Cars, Boolean> getIsAvailableCol(){
        TableColumn<Cars, Boolean> originCol = new TableColumn<>("isAvailable");
        originCol.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
        return originCol;
    }
    public static TableColumn<Cars, String> getCatgegoryCol(){
        TableColumn<Cars, String> originCol = new TableColumn<>("category");
        originCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        return originCol;
    } public static TableColumn<Cars, Integer> getSeatsCol(){
        TableColumn<Cars, Integer> originCol = new TableColumn<>("seats");
        originCol.setCellValueFactory(new PropertyValueFactory<>("seats"));
        return originCol;
    }

    public Cars(int id, String name, int price, String image, boolean isAvailable, String category, int seats) {
        this.id = id;
        this.name=name;
        this.image = image;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
        this.seats= seats;
    }
}
