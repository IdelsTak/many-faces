/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.PageHeaderController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class BrowserProfileList extends VBox {

    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(BrowserProfileList.class.getName());
    }

    /**
     Default constructor.
     */
    public BrowserProfileList() {
        super();
    }

    public Pane loadPane() {
        Pane headerPane = null;
        TabPane tabPane = null;

        try {
            headerPane = loadHeaderPane();
            tabPane = loadProfileListTab();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (headerPane != null && tabPane != null) {
            getChildren().setAll(headerPane, tabPane);
        }

        return this;
    }

    private Pane loadHeaderPane() throws IOException {
        URL location = getClass().getResource("/views/PageHeader.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = loader.load();
        PageHeaderController controller = loader.getController();

        controller.setHeaderText("Browser profile list");

        return pane;
    }

    private TabPane loadProfileListTab() throws IOException {
        URL location = getClass().getResource("/views/ProfileListTab.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        TabPane tabPane = loader.load();

        return tabPane;
    }

}
