package com.example.carrentalsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewsController {

    public void openPage( Stage stage, String page){
        stage.close();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page));

            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("CRS");
            stage.setScene(scene);

            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void openPayment( Stage stage, String page){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page));

            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("CRS");
            stage.setScene(scene);

            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
