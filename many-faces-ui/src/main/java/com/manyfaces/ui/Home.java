/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.NavigationBarController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Home extends HBox {

    private static final Logger LOG = Logger.getLogger(Home.class.getName());
    private Pane contentPane;

    /**
     Default constructor.
     */
    public Home() {
        super();
    }

    public Pane loadPane() {
        Pane navigationBar = null;

        try {
            navigationBar = loadNavigationBarWithHomeMenu();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (navigationBar != null) {
            contentPane = loadContentPane();

            getChildren().setAll(navigationBar, contentPane);
            HBox.setHgrow(contentPane, Priority.ALWAYS);
        }

        return this;
    }

    public Pane getContentPane() {
        return contentPane;
    }

    private Pane loadNavigationBarWithHomeMenu() throws IOException {
        URL location = getClass().getResource("/views/NavigationBar.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane navigationPane = loader.load();
        NavigationBarController controller = loader.getController();

        controller.loadHomeMenu(this);

        return navigationPane;
    }

    private Pane loadContentPane() {
        VBox vBox = new VBox();
        
        //A red border for testing purposes
        vBox.setStyle("-fx-border-color: red");

        vBox.setPrefHeight(632);
        vBox.setMinHeight(632);
        vBox.setMinWidth(706);
        vBox.setMaxWidth(Double.MAX_VALUE);

        return vBox;
    }

}
