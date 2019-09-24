/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.manyfaces.spi.RootComponent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileMenuController {

    private static final Logger LOG;
    @FXML
    private JFXButton goHomeButton;

    static {
        LOG = Logger.getLogger(ProfileMenuController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        goHomeButton.setOnAction(e -> {
            LOG.log(Level.INFO, "Show many-faces home");
            Lookup lkp = Lookup.getDefault();
            RootComponent rootComponent = lkp.lookup(RootComponent.class);

            rootComponent.resetContent();
        });
    }

}
