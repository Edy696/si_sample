/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.carservice.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;

import com.toyota.carservice.config.config;
import com.toyota.carservice.config.config2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Edi.Y
 */
public class CarMenuManagement implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    private Button resize;
    @FXML
    private Button fullscreen;
    @FXML
    private Label title;
    @FXML
    private AnchorPane paneDataCar;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;

    config2 con = new config2();
    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAdd;

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ApplicationContext appContex = config.getInstance().getApplicationContext();
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        Platform.runLater(() -> {
            stage = (Stage) maximize.getScene().getWindow();
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
            loadMenu();
        });
    }

    @FXML
    private void aksiMaximized(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            } else {
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");
                resize.setVisible(true);
            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");
            resize.setVisible(false);
        }
    }

    @FXML
    private void aksiminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        } else {
            stage.setIconified(true);
        }
    }

    @FXML
    private void aksiResize(ActionEvent event) {
    }

    @FXML
    private void aksifullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void aksiClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    protected void loadMenu() {
        CarMenuController carMenuController = (CarMenuController) con.loadAnchorPane(paneDataCar, "anchorMenu.fxml");
        carMenuController.setListener(this);
    }

    @FXML
    private void btnAddOnClick(ActionEvent event) {
        CarServiceController carServiceController = (CarServiceController) con.loadAnchorPane(paneDataCar, "anchorCar.fxml");
        carServiceController.setListener(this);
    }

    @FXML
    private void btnPlayOnClick() {
        CarServiceController carServiceController = (CarServiceController) con.loadAnchorPane(paneDataCar, "anchorCar.fxml");
        carServiceController.setListener(this);
    }

    @FXML
    private void aksiLogout(ActionEvent event) {
        config2 config = new config2();
        config.newStage2(stage, btnLogout,
                "/com/toyota/carservice/view/login.fxml", "Sample Apps", true,
                StageStyle.UNDECORATED, false);
    }

}
