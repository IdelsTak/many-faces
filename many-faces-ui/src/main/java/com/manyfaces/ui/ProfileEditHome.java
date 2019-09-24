/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileEditHome extends HBox {

    private static final Logger LOG;
    private Pane profileNavigationPane;
    private Pane profileContentPane;

    static {
        LOG = Logger.getLogger(ProfileEditHome.class.getName());
    }

    public ProfileEditHome() {
        super();
    }

    public Pane getPane() {
        try {
            getChildren().setAll(getProfileNavigationPane(), getProfileContentPane());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return this;
    }

    private Pane getProfileNavigationPane() throws IOException {
        if (profileNavigationPane == null) {
            URL location = getClass().getResource("/views/ProfileMenu.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            profileNavigationPane = loader.load();
        }
        return profileNavigationPane;
    }

    private Pane getProfileContentPane() {
        if (profileContentPane == null) {
            profileContentPane = new VBox();
            URL styleUrl = getClass().getResource("/styles/content-area.css");
            
            profileContentPane.getStylesheets().add(styleUrl.toExternalForm());
            profileContentPane.getStyleClass().add("content-area");
        }
        return profileContentPane;
    }

}
