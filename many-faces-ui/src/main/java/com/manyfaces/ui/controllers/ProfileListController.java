/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleNode;
import java.util.logging.Logger;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileListController {

    private static final Logger LOG;
    @FXML
    private TitledPane titledPane;
    @FXML
    private JFXCheckBox selectCheckBox;
    @FXML
    private JFXButton selectButton;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton moveToGroupButton;
    @FXML
    private JFXButton removeFromGroupButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXToggleNode settingsToggle;
    @FXML
    private FontIcon settingsIcon;
    @FXML
    private JFXToggleNode refreshButton;
    @FXML
    private FontIcon refreshIcon;
    @FXML
    private TableView<?> profilesTable;

    static {
        LOG = Logger.getLogger(ProfileListController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titledPane.addEventFilter(InputEvent.ANY, e -> {
            //If the target of the event is the title, ignore it
            //we want to be able to expand the titled pane using 
            //our custom button only
            if (((Styleable) e.getTarget())
                    .getStyleClass()
                    .contains("title")) {
                e.consume();
            }
        });

        titledPane.expandedProperty().addListener((o, oldVal, expanded) -> {
            selectCheckBox.setSelected(selectCheckBox.isSelected() 
                    && expanded != null 
                    && !expanded);
        });

        settingsToggle.setOnAction(e -> {
            titledPane.setExpanded(!titledPane.isExpanded());
        });

        settingsIcon.disableProperty()
                .bind(settingsToggle.selectedProperty().not());

        deleteButton.setDisable(true);
        moveToGroupButton.setDisable(true);
        removeFromGroupButton.setDisable(true);

        selectCheckBox.selectedProperty().addListener((o, oldVal, selected) -> {
            selectButton.setText(selected ? "Deselect all" : "Select all");
            deleteButton.setDisable(!selected);
            moveToGroupButton.setDisable(!selected);
            removeFromGroupButton.setDisable(!selected);
        });

        selectButton.setOnAction(e -> {
            selectCheckBox.setSelected(!selectCheckBox.isSelected());
        });
    }
}
