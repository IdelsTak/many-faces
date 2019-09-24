/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.ProfileEditHome;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileActionsPopupController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton moveButton;
    @FXML
    private JFXButton deleteButton;

    static {
        LOG = Logger.getLogger(ProfileActionsPopupController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
    }

    void setPopup(JFXPopup popup) {
        LOG.log(Level.INFO, "Show profile edit home");
        editButton.setOnAction(e -> {
            LOOKUP.lookup(RootComponent.class)
                    .setContent(new ProfileEditHome().getPane());
            popup.hide();
        });
    }

}
