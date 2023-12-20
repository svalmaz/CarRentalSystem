package com.example.carrentalsystem.Controller;

import com.example.carrentalsystem.DataBase.UserAuthCommands;
import com.example.carrentalsystem.Models.UserAuth;
import com.example.carrentalsystem.Models.UserReq;
import com.example.carrentalsystem.ViewsController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.util.Objects;

public class RegisterController {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    @FXML
    private Label message;
    @FXML
    private Button signIn;
    @FXML
    private Button signUp;


    public ViewsController vc = new ViewsController();
    public UserAuthCommands uaCom = new UserAuthCommands();
    public void singInCommand(ActionEvent event){
        vc.openPage((Stage) message.getScene().getWindow(), "login.fxml");
    }
    public void singUpCommand(ActionEvent event){
        if(!Objects.equals(name.getText(), "") && !Objects.equals(username.getText(), "") && !Objects.equals(pass.getText(), "")){
            UserReq ur = new UserReq(username.getText(), pass.getText(), name.getText());
            boolean urRes = uaCom.signUpReq(ur);
            System.out.println(urRes);
            if(Objects.equals(urRes, true)){
                vc.openPage((Stage) message.getScene().getWindow(), "login.fxml");

            }
            else{
                message.setText("Error!");
            }

        }
        else{
            message.setText("fill in the fields");
        }


    }




}
