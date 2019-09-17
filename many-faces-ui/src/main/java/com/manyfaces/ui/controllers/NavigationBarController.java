/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.manyfaces.ui.Home;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class NavigationBarController {

    private static final Logger LOG;
    @FXML
    private AnchorPane pane;

    static {
        LOG = Logger.getLogger(NavigationBarController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
    }
    
    public void loadHomeMenu(Home home) throws IOException{
        URL location = getClass().getResource("/views/HomeMenu.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane homeMenuPane = loader.load();
        HomeMenuController homeMenuController = loader.getController();
        
        homeMenuController.setHome(home);
        pane.getChildren().setAll(homeMenuPane);
        
        AnchorPane.setTopAnchor(homeMenuPane, 0.0);
        AnchorPane.setBottomAnchor(homeMenuPane, 0.0);
        AnchorPane.setLeftAnchor(homeMenuPane, 0.0);
        AnchorPane.setRightAnchor(homeMenuPane, 0.0);        
    }

}
