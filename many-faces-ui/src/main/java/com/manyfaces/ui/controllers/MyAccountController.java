/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.github.javafaker.Faker;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class MyAccountController {

    @FXML
    private Label emailLabel;
    @FXML
    private Label planLabel;
    @FXML
    private Hyperlink logsHyperlink;
    @FXML
    private JFXButton changePasswordButton;
    @FXML
    private JFXButton logoutButton;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        String email = new Faker().internet().emailAddress();
        emailLabel.setText(email);
    }

}
