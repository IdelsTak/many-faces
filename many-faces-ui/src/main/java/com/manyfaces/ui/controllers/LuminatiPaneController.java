/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.manyfaces.spi.RootComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.Notifications;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class LuminatiPaneController {

    private static final Logger LOG;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXButton activateButton;

    static {
        LOG = Logger.getLogger(LuminatiPaneController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        StackPane root = Lookup.getDefault()
                .lookup(RootComponent.class)
                .getRoot();

        Notifications notifications = Notifications.create()
                .owner(root)
                .text("Hello notification")
                .position(Pos.TOP_CENTER);

        activateButton.setOnAction(e -> {
            LOG.log(Level.INFO,
                    "\n++++++++++++++++++++++++++++"
                    + "\nActivate button clicked"
                    + "\n++++++++++++++++++++++++++++");
            notifications.show();
        });
    }

}
