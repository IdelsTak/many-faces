/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.manyfaces.spi.RootComponent;
import java.util.Objects;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileMenuController {

    private static final Logger LOG;
    @FXML
    private Label menuTitleLabel;
    @FXML
    private JFXButton goHomeButton;
    @FXML
    private TitledPane advancedMenuTitledPane;
    @FXML
    private RadioButton advancedMenuToggle;

    static {
        LOG = Logger.getLogger(ProfileMenuController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        advancedMenuTitledPane.addEventFilter(InputEvent.ANY, e -> {
            //If the target of the event is the title, ignore it
            //we want to be able to expand the titled pane using 
            //the advancedMenuToggle only
            if (((Styleable) e.getTarget())
                    .getStyleClass()
                    .contains("title")) {
                e.consume();
            }
        });

        BooleanProperty menuExpand = advancedMenuTitledPane.expandedProperty();
        BooleanProperty menuToggleSelected = advancedMenuToggle.selectedProperty();

        menuExpand.bind(menuToggleSelected);
        
        goHomeButton.setOnAction(e -> {
            Lookup lkp = Lookup.getDefault();
            RootComponent rootComponent = lkp.lookup(RootComponent.class);

            rootComponent.resetContent();
        });
    }

    public void setMenuTitle(String menuTitle) {
        String message = "Profile menu title should not be null";
        String title = Objects.requireNonNull(menuTitle, message);
        
        menuTitleLabel.setText(title);
    }
}
