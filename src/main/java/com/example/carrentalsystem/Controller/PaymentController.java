package com.example.carrentalsystem.Controller;

import com.example.carrentalsystem.DataBase.UserCommands;
import com.example.carrentalsystem.Models.Payment;
import com.example.carrentalsystem.Models.SharedDtaa;
import com.example.carrentalsystem.ViewsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class PaymentController {

    @FXML
    private TextField cardNumber;
    @FXML
    private TextField cvv;
    @FXML
    private TextField name;
    @FXML
    private TextField expiration;


    public void cancelBtnCommand(ActionEvent event) {
        ViewsController vs = new ViewsController();
        vs.openPayment((Stage) name.getScene().getWindow(), "main.fxml");


    }

    public void payBtnCommand(ActionEvent event) throws SQLException {
        SharedDtaa s = new SharedDtaa();

        UserCommands cm = new UserCommands();
        cm.addPayment(new Payment(name.getText(),cvv.getText(),cardNumber.getText(), expiration.getText(),s.getSomeData()));

        cm.addOrders(s.getSomeOrder());
        ViewsController vs = new ViewsController();
        vs.openPayment((Stage) name.getScene().getWindow(), "main.fxml");


    }
}
