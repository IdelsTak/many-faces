/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class EditGroupsDialogController {

    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton cancelButton;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

    void setDialog(JFXDialog dialog) {
        if (dialog == null) {
            throw new IllegalArgumentException("Dialo should not be null");
        }
        closeButton.setOnAction(e -> dialog.close());
        cancelButton.setOnAction(e -> dialog.close());
    }

}
