/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXTabPane;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileListTabController {

    private static final Logger LOG;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab profileListTab;
    @FXML
    private Tab groupsTab;

    static {
        LOG = Logger.getLogger(ProfileListTabController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
//        LOG.log(Level.INFO, "Style: {0}", tabPane);
    }
}
