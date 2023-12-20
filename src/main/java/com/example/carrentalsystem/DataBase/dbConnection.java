package com.example.carrentalsystem.DataBase;

import java.sql.*;

public class dbConnection {
    public Connection dataBaseLink = null;

    public Connection getConnection(){
        String dataBase = "";
        String dataBaseUser = "postgres";
        String dataBasePass = "123456";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try{

            dataBaseLink = DriverManager.getConnection(url,dataBaseUser, dataBasePass);
            System.out.println("Ok");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Ok1");
        }
        return dataBaseLink;
    }
    public void checkDB(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            if (!checkTableExists(con, "cars")) {
                createCarsTable(con);
            }
            if (!checkTableExists(con, "users")) {
                createUsersTable(con);
            }
            if (!checkTableExists(con, "orders")) {
                createOrdersTable(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    private static boolean checkTableExists(Connection con, String tableName) throws SQLException {
        try (ResultSet rs = con.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }

    private static void createCarsTable(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE cars (" +
                            "car_id SERIAL PRIMARY KEY," +
                            "car_category VARCHAR(255)," +
                            "car_name VARCHAR(255),"+
                            "car_seats INT," +
                            "car_image VARCHAR(255),"+
                            "car_price INT," +
                            "is_available BOOLEAN)"
            );
        }
    }

    private static void createUsersTable(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE users (" +
                            "user_id SERIAL PRIMARY KEY," +
                            "user_name VARCHAR(255)," +
                            "user_login VARCHAR(255)," +
                            "user_pass VARCHAR(255)," +
                            "user_status VARCHAR(255))"
            );
        }
    }

    private static void createOrdersTable(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE orders (" +
                            "order_id SERIAL PRIMARY KEY," +
                            "orders_user_id INT REFERENCES users(user_id)," +
                            "orders_car_id INT REFERENCES cars(car_id)," +
                            "orders_start_date DATE," +
                            "orders_end_date DATE)"
            );
        }
    }
}
