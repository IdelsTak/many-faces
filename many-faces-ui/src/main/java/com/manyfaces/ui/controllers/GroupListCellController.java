/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class GroupListCellController {

    @FXML
    private Label groupNameLabel;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

    void setGroupName(String groupName) {
        String name = Objects.requireNonNull(
                groupName, 
                "Group name should not be null");
        
        groupNameLabel.setText(name);
    }

}
