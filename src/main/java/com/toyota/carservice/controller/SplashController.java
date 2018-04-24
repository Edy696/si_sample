/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toyota.carservice.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.springframework.context.ApplicationContext;

import com.toyota.carservice.animations.FadeInLeftTransition;
import com.toyota.carservice.animations.FadeInRightTransition;
import com.toyota.carservice.animations.FadeInTransition;
import com.toyota.carservice.config.config;
import com.toyota.carservice.config.config2;

/**
 * FXML Controller class
 *
 * @author Edi.Y
 */
public class SplashController implements Initializable {

    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblRudy;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    Stage stage;
    @FXML
    private ImageView imgLoading;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        longStart();
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
        // TODO
    }

    private void longStart() {
        Service<ApplicationContext> service = new Service<ApplicationContext>() {
            @Override
            protected Task<ApplicationContext> createTask() {
                return new Task<ApplicationContext>() {
                    @Override
                    protected ApplicationContext call() throws Exception {
                        ApplicationContext appContex = config.getInstance().getApplicationContext();
                        int max = appContex.getBeanDefinitionCount();
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(50);
                            updateProgress(k + 1, max);
                        }
                        return appContex;
                    }
                };
            }
        };
        service.start();
        service.setOnRunning((WorkerStateEvent event) -> {
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInRightTransition(lblRudy).play();
            new FadeInTransition(vboxBottom).play();
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            config2 config = new config2();
            config.newStage(stage, lblClose, "/com/toyota/carservice/view/formSwitchMode.fxml", "Aplikasi Informasi Service", true, StageStyle.UNDECORATED, false);
        });
    }
}
