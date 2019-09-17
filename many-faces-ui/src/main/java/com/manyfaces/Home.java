/*
 Copyright 2019.
 */
package com.manyfaces;

import com.manyfaces.ui.controllers.NavigationBarController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
class Home extends AnchorPane {

    private static final Logger LOG = Logger.getLogger(Home.class.getName());

    /**
     Default constructor.
     */
    Home() {
    }

    Pane loadPane() {
        Pane navigationBar = null;
        
        try {
            navigationBar = loadNavigationBarWithHomeMenu();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (navigationBar != null) {
            Pane contentPane = loadContentPane();
            
            getChildren().setAll(navigationBar, contentPane);

            AnchorPane.setTopAnchor(navigationBar, 0.0);
            AnchorPane.setBottomAnchor(navigationBar, 0.0);
            AnchorPane.setLeftAnchor(navigationBar, 0.0);
            AnchorPane.setTopAnchor(contentPane, 0.0);
            AnchorPane.setBottomAnchor(contentPane, 0.0);
            AnchorPane.setRightAnchor(contentPane, 0.0);
        }
        
        return this;
    }

    private Pane loadNavigationBarWithHomeMenu() throws IOException {
        URL location = getClass().getResource("/views/NavigationBar.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane navigationPane = loader.load();
        NavigationBarController controller = loader.getController();
        
        controller.loadHomeMenu();

        return navigationPane;
    }

    private Pane loadContentPane() {
        AnchorPane contentPane = new AnchorPane();

        contentPane.setPrefWidth(706);
        contentPane.setPrefHeight(632);
        contentPane.setMinWidth(706);
        contentPane.setMinHeight(632);

        return contentPane;
    }

}
