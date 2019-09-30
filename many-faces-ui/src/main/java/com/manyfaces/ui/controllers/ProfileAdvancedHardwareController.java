/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.github.javafaker.Faker;
import com.manyfaces.ui.controllers.AlertInfoController.Style;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAdvancedHardwareController {

    private static final Logger LOG;
    @FXML
    private RadioButton canvasNoiseToggle;
    @FXML
    private RadioButton canvasOffToggle;
    @FXML
    private RadioButton canvasBlockToggle;
    @FXML
    private BorderPane canvasAlertPane;
    @FXML
    private Label canvasHashLabel;

    static {
        LOG = Logger.getLogger(ProfileAdvancedHardwareController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        canvasNoiseToggle.setOnAction(e -> {
            if (canvasNoiseToggle.isSelected()) {

                Pane alertPane = getAlertPane("Canvas fingerprint will be masked "
                        + "by applying a "
                        + "unique and persistent noise. If you launch the same "
                        + "browser profile on different hardware the resulting "
                        + "canvas fingerprint may differ. This option is most "
                        + "popular for web scraping.", Style.INFO);
                canvasAlertPane.setCenter(alertPane);
            }
        });
        canvasOffToggle.setOnAction(e -> {
            if (canvasOffToggle.isSelected()) {

                Pane alertPane = getAlertPane("Don't mask Canvas. "
                        + "Suitable for most users.", Style.INFO);
                canvasAlertPane.setCenter(alertPane);
            }
        });
        canvasBlockToggle.setOnAction(e -> {
            if (canvasBlockToggle.isSelected()) {

                Pane alertPane = getAlertPane("Block - Block websites from "
                        + "reading Canvas "
                        + "fingerprint. This is a legacy mode and it's not "
                        + "advised to use.", Style.WARNING);
                canvasAlertPane.setCenter(alertPane);
            }
        });
        
        Platform.runLater(() -> canvasNoiseToggle.fireEvent(new ActionEvent()));
        
        canvasHashLabel.setText(new Faker().internet().uuid());
    }

    private Pane getAlertPane(String message, Style style) {
        URL location = getClass().getResource("/views/AlertInfo.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = null;

        try {
            pane = loader.load();

            AlertInfoController controller = loader.getController();
            controller.setMessage(message, style);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return pane;
    }

}
