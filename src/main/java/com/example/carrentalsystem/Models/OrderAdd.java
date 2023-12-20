package com.example.carrentalsystem.Models;

import java.time.LocalDate;

public class OrderAdd {
    public int orderId; // Assuming order_id is an auto-generated field
    public int userId; // orders_user_id
    public int carId; // orders_car_id
    public String startDate; // orders_start_date
    public String endDate; // orders_end_date

    // Constructor
    public OrderAdd(int userId, int carId, String startDate, String endDate) {
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
