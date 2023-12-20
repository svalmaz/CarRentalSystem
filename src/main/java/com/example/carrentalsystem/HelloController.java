package com.example.carrentalsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage1 = new Stage();
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.initStyle(StageStyle.UNDECORATED);
            stage1.setTitle("ABC");
            stage1.setScene(new Scene(root1));
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}