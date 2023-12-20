package com.example.carrentalsystem.Models;

public class SharedDtaa {
    private static int someData;
    private static OrderAdd ord;
    public OrderAdd getSomeOrder() {
        return ord;
    }

    public void setSomeOrder(OrderAdd ord) {
        this.ord = ord;
    }

    public int getSomeData() {
        return someData;
    }

    public void setSomeData(int someData) {
        this.someData = someData;
    }
}
