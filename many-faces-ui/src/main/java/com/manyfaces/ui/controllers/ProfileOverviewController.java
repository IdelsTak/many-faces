/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileOverviewController
        implements ProfileMenuController.ProfileMenuChildController {

    @FXML
    private JFXButton editProxyButton;
    @FXML
    private Hyperlink timezoneHyperlink;
    @FXML
    private Hyperlink webRtcHyperlink;
    @FXML
    private Hyperlink geolocationHyperlink;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        editProxyButton.setOnAction((event) -> {
        });
    }

    @Override
    public void setProfileMenuController(ProfileMenuController menuController) {
        String message = "Profile menu controller should not be null";
        ProfileMenuController kontroller = Objects.requireNonNull(menuController, message);

        editProxyButton.setOnAction(e -> kontroller.setProxyContent());
        timezoneHyperlink.setOnAction(e -> kontroller.setTimezoneContent());
        webRtcHyperlink.setOnAction(e -> kontroller.setWebRtcContent());
    }

}
