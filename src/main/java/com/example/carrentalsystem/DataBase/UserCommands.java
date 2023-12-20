package com.example.carrentalsystem.DataBase;

import com.example.carrentalsystem.Models.OrderAdd;
import com.example.carrentalsystem.Models.Payment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserCommands {
    public void addOrders(OrderAdd order) throws SQLException {

        try {
            System.out.println("register");

            dbConnection conNow = new dbConnection();
            Connection conn = conNow.getConnection();



            System.out.println("asd1111");
            //
            String add = "INSERT INTO orders (orders_user_id, orders_car_id, orders_start_date, orders_end_date) VALUES ("+order.getUserId()+", "+order.getCarId()+", '"+order.getStartDate()+"','"+order.getEndDate()+"');";
            System.out.println(add);
            Statement statement2 = conn.createStatement();
            statement2.executeUpdate(add);
            changeAvailable(order);



        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
    public void addPayment(Payment pay) throws SQLException {
       String add =  "INSERT INTO payments (payment_number, payment_cvv, payment_name, payment_date, payment_user_id) VALUES ('"+pay.cardNumber+"', '"+pay.cvv+"', '"+pay.name+"', '"+pay.expiration+"', "+pay.userId+")";
       System.out.println(add);
        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();

        Statement statement1 = conn.createStatement();
        statement1.executeUpdate(add);
    }
    public void changeAvailable(OrderAdd order) throws SQLException {
        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();
        String register = "UPDATE Cars SET is_available = false WHERE car_id = "+order.getCarId();

        Statement statement1 = conn.createStatement();
        statement1.executeUpdate(register);
    }
}
