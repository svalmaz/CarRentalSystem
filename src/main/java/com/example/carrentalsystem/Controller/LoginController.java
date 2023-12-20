package com.example.carrentalsystem.Controller;

import com.example.carrentalsystem.DataBase.UserAuthCommands;
import com.example.carrentalsystem.Models.SharedDtaa;
import com.example.carrentalsystem.Models.UserAuth;
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
import org.w3c.dom.events.MouseEvent;

import java.util.Objects;

public class LoginController{

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
public void singUpCommand(ActionEvent event){
    vc.openPage((Stage) message.getScene().getWindow(), "register.fxml");
}
public void signInCommand(ActionEvent event){

    UserAuth ua = new UserAuth(username.getText(), pass.getText());
    String signInRes = uaCom.signInReq(ua);
    if(Objects.equals(signInRes, "admin")){
        vc.openPage((Stage) message.getScene().getWindow(), "admin.fxml");

    }
    else if(Objects.equals(signInRes, "user")){

        vc.openPage((Stage) message.getScene().getWindow(), "main.fxml");

    }
    else{
        message.setText("Username or pass is incorrect");
    }
    SharedDtaa s = new SharedDtaa();
    System.out.println(s.getSomeData());

}


}
