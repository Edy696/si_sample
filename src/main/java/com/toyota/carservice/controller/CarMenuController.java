package com.toyota.carservice.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;

import com.toyota.carservice.config.config;
import com.toyota.carservice.config.config2;
import com.toyota.carservice.implement.CarDaoImpl;
import com.toyota.carservice.interfaces.CarDao;
import com.toyota.carservice.model.Car;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CarMenuController implements Initializable {

    @FXML
    private AnchorPane paneData;

    @FXML
    private AnchorPane paneMenu;

    @FXML
    private ProgressBar bar;

    @FXML
    private ImageView imgLoad;

    config2 con = new config2();

    CarDao carDaoImpl;

    private CarMenuManagement carMenuManagement;

    protected void loadCar(Car car) {
        CarServiceController carServiceController;
        carServiceController = (CarServiceController) con.loadAnchorPane(
                paneData, "anchorCar.fxml");
        carServiceController.setCar(car);
        carServiceController.setListener(carMenuManagement);
    }

    public void setListener(CarMenuManagement carMenuManagement) {
        this.carMenuManagement = carMenuManagement;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            System.out.println("=============================");
            ApplicationContext ctx = config.getInstance().getApplicationContext();
            carDaoImpl = ctx.getBean(CarDaoImpl.class);
            List<Car> listCar = carDaoImpl.findAll();
            System.out.println("=============================");
            HBox hBox = new HBox();
            for (Car c : listCar) {
                System.out.println(c.getCarId());
                System.out.println(c.getName());
                System.out.println(c.getPhoto());
                Button btn = new Button();
                btn.setId(c.getCarId().toString());
                btn.setText(c.getName());
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        loadCar(c);
                    }
                });
                hBox.getChildren().add(btn);
            }
            paneMenu.getChildren().add(hBox);
        });
    }
}
