/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfilePaneController {

    @FXML
    private JFXCheckBox titlePaneCheckBox;
    @FXML
    private TitledPane titledPane;
    @FXML
    private Text profileIdText;
    @FXML
    private HBox titleHbox;
    @FXML
    private Label profileNameLabel;
    @FXML
    private JFXToggleNode expandPaneToggle;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titledPane.widthProperty().addListener((obs, ov, nv) -> {
            titleHbox.setPrefWidth(nv.doubleValue() - 0.0);
        });

        titledPane.expandedProperty().addListener((obs, ov, nv) -> {
            expandPaneToggle.setSelected(nv);
        });

        expandPaneToggle.selectedProperty().addListener((ob, ov, nv) -> {
            titledPane.setExpanded(nv);
        });

    }

    void setTitledPaneTitle(String profileName) {
        profileNameLabel.setText(profileName);
    }

    void setTitledPaneSelected(boolean selected) {
        titlePaneCheckBox.setSelected(selected);
    }

    void showSelectBoxes(boolean show) {
        if (show) {
            titleHbox.getChildren().set(0, titlePaneCheckBox);
        } else {
            titleHbox.getChildren().remove(titlePaneCheckBox);
        }
    }

}
