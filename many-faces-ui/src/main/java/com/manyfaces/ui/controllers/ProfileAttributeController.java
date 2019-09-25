/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAttributeController {

    @FXML
    private Text headerText;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }
    
    public void setHeaderText(String text){
        headerText.setText(text);
    }

}
