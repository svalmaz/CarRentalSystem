package com.example.carrentalsystem.DataBase;

import com.example.carrentalsystem.Models.SharedDtaa;
import com.example.carrentalsystem.Models.UserAuth;
import com.example.carrentalsystem.Models.UserReq;
import com.example.carrentalsystem.Utils.PassConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserAuthCommands {


    public PassConverter passConverter = new PassConverter();
    public String signInReq(UserAuth user){
         dbConnection conNow = new dbConnection();
         Connection conn = conNow.getConnection();

        try{
            String hashedpass = passConverter.hashPassword(user.pass);
            String req = "SELECT * FROM users where user_login = '"+user.username+"' and user_pass = '"+hashedpass+"'";

            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(req);
            while(queryResult.next()){

                    String status = queryResult.getString(5);
                System.out.println(status);
                SharedDtaa s = new SharedDtaa();
                s.setSomeData(queryResult.getInt(1));
                System.out.println(queryResult.getInt(1));
                   return(status);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

        return("false");
    }
    public boolean signUpReq(UserReq user){
        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();
        String login = "SELECT count(1) FROM users where user_login = '"+user.username+"'";

        try{

            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(login);
            while(queryResult.next()){
                if(queryResult.getInt(1)>=1){

                    return(false);
                }
                else{

                    String hashedpass = passConverter.hashPassword(user.pass);
                    String register = "INSERT INTO Users (user_name, user_login, user_pass, user_status) VALUES ('"+user.name+"', '"+user.username+"', '"+hashedpass+"','user')";
                    Statement statement1 = conn.createStatement();
                    System.out.println("asdasdasd");
                    statement1.executeQuery(register);


                    return(true);
                }
            }
        }

        catch (Exception e1){
            System.out.println(e1.getMessage());
        }


        return true;
    }


}
