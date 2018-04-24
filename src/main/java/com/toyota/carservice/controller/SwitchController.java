/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.carservice.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.toyota.carservice.animations.FadeInTransition;
import com.toyota.carservice.config.config2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Edi.Y
 */
@SuppressWarnings("restriction")
public class SwitchController implements Initializable {

    @FXML
    private Button btnKelolaData;
    @FXML
    private Button btnInformasiService;
    @FXML
    private Label lblClose;
    Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            new FadeInTransition(btnInformasiService).play();
            new FadeInTransition(btnKelolaData).play();
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });
    }

    @FXML
    private void aksiKelolaData(ActionEvent event) {
        config2 c = new config2();
        c.newStage(stage, lblClose, "/com/toyota/carservice/view/formCarMenu.fxml", "Kelola Data Informasi Service", true, StageStyle.DECORATED, false);
    }

    @FXML
    private void aksiInformasiService(ActionEvent event) {
        config2 c = new config2();
        c.newStage(stage, lblClose, "/com/toyota/carservice/view/formServiceInformation.fxml", "Informasi Data Service", true, StageStyle.DECORATED, false);
    }

}
