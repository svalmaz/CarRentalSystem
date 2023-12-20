package com.example.carrentalsystem.Controller;


import com.example.carrentalsystem.DataBase.AdminCommands;
import com.example.carrentalsystem.DataBase.UserCommands;
import com.example.carrentalsystem.DataBase.dbConnection;
import com.example.carrentalsystem.Models.CarSearch;
import com.example.carrentalsystem.Models.Cars;
import com.example.carrentalsystem.Models.OrderAdd;
import com.example.carrentalsystem.Models.SharedDtaa;
import com.example.carrentalsystem.ViewsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public AdminCommands ac = new AdminCommands();

    @FXML
    public Button searchBtnCommand;
    @FXML
    private TableColumn<Cars, String> name;
    @FXML
    private TableColumn<Cars, String> image;
    @FXML
    private TableColumn<Cars, Integer> price;
    @FXML
    private TableColumn<Cars, Integer> id;
    @FXML
    private TableColumn<Cars, Boolean> isAvailable;
    @FXML
    private TableColumn<Cars, String> category;
    @FXML
    private TableColumn<Cars, Integer> seats;
    @FXML
    private TableView<Cars> table;
    @FXML
    private TextField nameText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField searchName;
    @FXML
    private DatePicker startDateAdd;
    @FXML
    private DatePicker endDateAdd;
    @FXML
    private CheckBox searchIsAvailable;
    @FXML
    private ComboBox<String>   searchCategory;
    @FXML
    private ComboBox<String> searchSeats;
    @FXML
    private TextField searchPriceMin;
    @FXML
    private TextField searchPriceMax;
    @FXML
    private Button selectImageBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button update;
    @FXML
    private ImageView imageView = null;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ComboBox<String> seatsComboBox;
    @FXML
    private Label seatsAdd;
    @FXML
    private Label categoryAdd;
    @FXML
    private Label priceAdd;
    @FXML
    private Label nameAdd;


    private int carId;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDataToComboBox();
        editData();
    }
    private void editData(){

        name.setCellValueFactory(new PropertyValueFactory<Cars, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("price"));
        id.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("id"));
        image.setCellValueFactory(new PropertyValueFactory<Cars, String>("image"));
        seats.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("seats"));
        category.setCellValueFactory(new PropertyValueFactory<Cars, String>("category"));
        table.setItems(initialData());


        carId = -1;
    }

    ObservableList<Cars> initialData() {
        ObservableList<Cars> c1 = FXCollections.observableArrayList();

        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();
        String login = "SELECT * FROM cars WHERE is_Available != false";


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
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        int index =table.getSelectionModel().getSelectedIndex();
        if (index<=-1){
            return;
        }
        nameAdd.setText(name.getCellData(index).toString());
        priceAdd.setText(price.getCellData(index).toString());
        String asd = image.getCellData(index).toString();
        seatsAdd.setText(seats.getCellData(index).toString());
        categoryAdd.setText(category.getCellData(index).toString());

        Image image = new Image(asd);
        imageView.setImage(image);

        carId = id.getCellData(index);

    }

    public void searchBtn1Command(ActionEvent event) {
        CarSearch car = new CarSearch();
        if(Objects.equals(searchCategory.getValue(), "All")){
            car.car_category = searchCategory.getValue() == null || searchCategory.getValue().isEmpty() ? null :  null;

        }
        else{
            car.car_category = searchCategory.getValue() == null || searchCategory.getValue().isEmpty() ? null :  "'"+searchCategory.getValue()+"'";

        }
        if(Objects.equals(searchSeats.getValue(), "All")){
            car.car_seats = searchSeats.getValue() == null ? null : null;

        }
        else{
            car.car_seats = searchSeats.getValue() == null ? null : searchSeats.getValue();

        }
        car.car_name = searchName.getText().isEmpty() ? null :  "'"+searchName.getText()+"'";
        car.car_pricemax = searchPriceMax.getText().isEmpty() ? null : searchPriceMax.getText();
        car.car_pricemin = searchPriceMin.getText().isEmpty() ? null : searchPriceMin.getText();
        car.isAvailable = true;

        table.setItems(ac.searchCar(car));
    }

    public void updateCommand(ActionEvent event) {
        editData();
    }

    public void myOrdersBtnCommand(ActionEvent event) {

    }

    public void rentBtnCommand(ActionEvent event) throws SQLException {
        if(carId == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Choose data", ButtonType.OK);
            alert.showAndWait();


        }
        else{
            SharedDtaa s = new SharedDtaa();
            OrderAdd ord = new OrderAdd(s.getSomeData(), carId, String.valueOf(startDateAdd.getValue()), String.valueOf(endDateAdd.getValue()));
            s.setSomeOrder(ord);
            ViewsController vs = new ViewsController();
            vs.openPayment((Stage) nameAdd.getScene().getWindow(), "payment.fxml");

            carId = -1;
        }
    }
    public void addDataToComboBox(){

        searchSeats.setItems(FXCollections.observableArrayList(
                new String("All"),
                new String("1"),
                new String("2"),
                new String("3"),
                new String("4"),
                new String("5"),
                new String("6")

        ));
        searchCategory.setItems(FXCollections.observableArrayList(
                new String("All"),
                new String("Microcar / kei car"),
                new String("A-segment / City car / Minicompact"),
                new String("B-segment / Supermini / Subcompact"),
                new String("C-segment / Small family / Compact"),
                new String("D-segment / Large family / Mid-size"),
                new String("E-segment / Executive / Full-size"),
                new String("F-segment / Luxury saloon / Full-size luxury")

        ));}

        private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

}
