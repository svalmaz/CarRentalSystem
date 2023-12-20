package com.example.carrentalsystem.DataBase;

import com.example.carrentalsystem.Models.CarAdd;
import com.example.carrentalsystem.Models.CarSearch;
import com.example.carrentalsystem.Models.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminCommands {

    public void addCar(CarAdd car) throws IOException {
        try {
            dbConnection conNow = new dbConnection();
            Connection conn = conNow.getConnection();
            String imgUrl = saveImg(car.iconimage);
            String register = "INSERT INTO Cars (car_name, car_price, car_image, is_available,car_category, car_seats) VALUES ('"+car.name+"', '"+car.price+"', '"+imgUrl+"', true, '"+car.categoryName+"',"+car.seatsNumber+")";
            Statement statement1 = conn.createStatement();
            statement1.executeQuery(register);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public String saveImg(File iconimage) throws IOException {

        int count = getCount("SELECT count(1) FROM cars ");
        File destinationFile = new File("C:/Users/almaz/Documents/CarRentalSystem/src/main/java/com/example/carrentalsystem/Images/"+String.valueOf(count+1)+"saved_image.png");

        Files.copy(iconimage.toPath(),  destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return (destinationFile.getPath());


    }
    public void deleteCar(int Id) throws SQLException {
        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();
       String register ="DELETE FROM cars WHERE  car_id ="+Id;
        Statement statement1 = conn.createStatement();
        statement1.executeQuery(register);
    }
    public void updateCar(CarAdd car, int Id, String img){
        try {
            System.out.println("register");

            dbConnection conNow = new dbConnection();
            Connection conn = conNow.getConnection();
            String pathToNewFile = car.iconimage.getPath();
            String pathToOldFile = img;
                File destinationFile = new File(img);

            Files.copy(Paths.get(pathToNewFile), Paths.get(pathToOldFile), StandardCopyOption.REPLACE_EXISTING);

                String register = "UPDATE Cars SET car_seats = "+car.seatsNumber+", car_category = '"+car.categoryName+"', car_name='"+car.name+"', car_price="+car.price+" WHERE car_id = "+Id+"";

            Statement statement1 = conn.createStatement();
                statement1.executeQuery(register);





        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Cars> searchCar(CarSearch car){
        ObservableList<Cars> c1 = FXCollections.observableArrayList();

        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();
        String login = "SELECT * FROM cars WHERE (car_name = "+ car.car_name+" OR  "+ car.car_name+" IS NULL) AND (is_available = "+car.isAvailable+" OR "+car.isAvailable+" IS NULL) AND (car_category = "+car.car_category+" OR "+car.car_category+" IS NULL) AND (car_seats = "+car.car_seats+" OR "+car.car_seats+" IS NULL) AND (car_price >= "+car.car_pricemin+" OR "+car.car_pricemin+" IS NULL) AND (car_price <="+ car.car_pricemax+" OR "+car.car_pricemax+" IS NULL);";

        System.out.println(login);
        try {
            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(login);
            while (queryResult.next()) {
                Cars car1 = new Cars(queryResult.getInt(1), queryResult.getString(3), queryResult.getInt(6), queryResult.getString(5), queryResult.getBoolean(7), queryResult.getString(2), queryResult.getInt(4));

                c1.add(car1);
            }
            return c1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCount(String req){
        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();

        try {

            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(req);
            while (queryResult.next()) {


                return (queryResult.getInt(1));
            }

        }
        catch (Exception exp){
            System.out.println("Ok1");
            System.out.println(exp.getMessage());
            return(1000000);
        }
        return (1000000);
    }
}
