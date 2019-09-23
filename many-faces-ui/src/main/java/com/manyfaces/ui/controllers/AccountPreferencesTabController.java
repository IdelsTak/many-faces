/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXTabPane;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class AccountPreferencesTabController {

    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab accountTab;
    @FXML
    private Tab preferencesTab;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

    public void setPageHeaderController(PageHeaderController phc) {
        String message = "Header controller should not be null";
        PageHeaderController controller = Objects.requireNonNull(phc, message);
        
        SingleSelectionModel<Tab> ssm = tabPane.getSelectionModel();
        
        ssm.selectedIndexProperty().addListener((o, ov, nv) -> {
            setHeaderText(nv.intValue(), controller);
        });

        setHeaderText(ssm.getSelectedIndex(), controller);
    }
    
    private void setHeaderText(int idx, PageHeaderController controller) {
        switch (idx) {
            case 0:
                controller.setHeaderText("My account");
                return;
            case 1:
                controller.setHeaderText("Preferences");
                return;
            default:
                throw new IllegalArgumentException("Tab index not known");
        }
    }

}