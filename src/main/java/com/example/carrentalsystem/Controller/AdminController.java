package com.example.carrentalsystem.Controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.carrentalsystem.Models.*;
import com.example.carrentalsystem.DataBase.*;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable   {


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
    public void getSelected(MouseEvent event){


    }
    private File iconimage;
    private Image img;
    private String imgUrl;
    private int carId;
    public AdminCommands ac = new AdminCommands();

    ObservableList<Cars> initialData() {
        ObservableList<Cars> c1 = FXCollections.observableArrayList();

        dbConnection conNow = new dbConnection();
        Connection conn = conNow.getConnection();
        String login = "SELECT * FROM cars";


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

        isAvailable.setCellValueFactory(new PropertyValueFactory<Cars, Boolean>("isAvailable"));
        table.setItems(initialData());
        nameText.setText("");
        priceText.setText("");
        imageView.setImage(null);

        carId = -1;
    }

    public void selectImageBtnCommand(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        iconimage = filechooser.showOpenDialog(selectImageBtn.getScene().getWindow());
        System.out.println(iconimage.getName());
        if (iconimage != null) {
            String iconimagepath = iconimage.toURI().toString();
            System.out.println(iconimagepath);

            Image image = new Image(iconimagepath);
            imageView.setImage(image);
            img = image;
        }
    }
    public void deleteBtnCommand(ActionEvent event) throws SQLException {
        if (carId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Choose data", ButtonType.OK);
            alert.showAndWait();


        }
        else{
            ac.deleteCar(carId);
        }

    }

    public void saveBtnCommand(ActionEvent event) {
        if(carId == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Choose data", ButtonType.OK);
            alert.showAndWait();


        }
        else{ boolean check= check();
            if(check){
                CarAdd car = new CarAdd(nameText.getText(), Integer.valueOf( priceText.getText()),iconimage , categoryComboBox.getValue(),  seatsComboBox.getValue());
                nameText.setText("");
                priceText.setText("");
                imageView.setImage(null);
                categoryComboBox.getSelectionModel().select("car category");
                seatsComboBox.getSelectionModel().select("seats number");
                System.out.println(imgUrl + iconimage.getPath());
                ac.updateCar(car, carId,imgUrl);

                editData();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Choose data", ButtonType.OK);
                alert.showAndWait();
            }

        }
    }
    public void searchBtn1Command(ActionEvent event) throws IOException {
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
        car.isAvailable = searchIsAvailable.isSelected();

        table.setItems(ac.searchCar(car));
    }
    public void updateCommand(ActionEvent event) {

       System.out.println(categoryComboBox.valueProperty().getValue());

        editData();
    }
    public void addBtnCommand(ActionEvent event) throws IOException {

        boolean check= check();
        if(check){
            CarAdd car = new CarAdd(nameText.getText(), Integer.valueOf( priceText.getText()),iconimage, categoryComboBox.getValue(),  seatsComboBox.getValue() );
            nameText.setText("");
            priceText.setText("");
            imageView.setImage(null);
            categoryComboBox.getSelectionModel().select("car category");
            seatsComboBox.getSelectionModel().select("seats number");

            ac.addCar(car);

            editData();
        }
        else{
            System.out.println("asdasdasd");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Choose data", ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        int index =table.getSelectionModel().getSelectedIndex();
        if (index<=-1){
            return;
        }
        nameText.setText(name.getCellData(index).toString());
        priceText.setText(price.getCellData(index).toString());
        String asd = image.getCellData(index).toString();
        seatsComboBox.getSelectionModel().select(seats.getCellData(index).toString());
        categoryComboBox.getSelectionModel().select(category.getCellData(index).toString());

        Image image = new Image(asd);
        imageView.setImage(image);
        imgUrl = asd;
        carId = id.getCellData(index);

    }
    public void addDataToComboBox(){
        categoryComboBox.setItems(FXCollections.observableArrayList(
                new String("Microcar / kei car"),
                new String("A-segment / City car / Minicompact"),
                new String("B-segment / Supermini / Subcompact"),
                new String("C-segment / Small family / Compact"),
                new String("D-segment / Large family / Mid-size"),
                new String("E-segment / Executive / Full-size"),
                new String("F-segment / Luxury saloon / Full-size luxury")

        ));

        seatsComboBox.setItems(FXCollections.observableArrayList(
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

        ));

        searchSeats.setItems(FXCollections.observableArrayList(
                new String("All"),
                new String("1"),
                new String("2"),
                new String("3"),
                new String("4"),
                new String("5"),
                new String("6")

        ));
    }

    public boolean check(){
        try{
        if(imageView.getImage().toString().length() > 10 && !Objects.equals(nameText.getText(), "") && !Objects.equals(priceText.getText(), "")){


            return(true);
        }
        else{

            return (false);
        }}
        catch (Exception e){
            return (false);
        }
    }
}
