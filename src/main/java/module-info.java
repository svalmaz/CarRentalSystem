module com.example.carrentalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.carrentalsystem to javafx.fxml;


    exports com.example.carrentalsystem.Controller;
    opens com.example.carrentalsystem.Controller to javafx.fxml;
    exports com.example.carrentalsystem.Models;
    opens com.example.carrentalsystem.Models to javafx.fxml;
    exports com.example.carrentalsystem;
}