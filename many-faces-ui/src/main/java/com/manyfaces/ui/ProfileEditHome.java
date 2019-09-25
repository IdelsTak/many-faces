/*
 Copyright 2019.
 */
package com.manyfaces.ui;

import com.manyfaces.ui.controllers.ProfileAttributeController;
import com.manyfaces.ui.controllers.ProfileMenuController;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileEditHome extends HBox {

    private static final Logger LOG;
    private Pane profileNavigationPane;
    private Pane profileContentPane;
    private final String menuTitle;
    private ProfileAttributeController attributeController;

    static {
        LOG = Logger.getLogger(ProfileEditHome.class.getName());
    }

    public ProfileEditHome(String menuTitle) {
        super();
        this.menuTitle = menuTitle;
    }

    public Pane getPane() {
        try {
            getChildren().setAll(getProfileNavigationPane(), getProfileContentPane());
            HBox.setHgrow(getProfileContentPane(), Priority.ALWAYS);
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
            ProfileMenuController menuController = loader.getController();
            menuController.setMenuTitle(menuTitle);
            
            //Ensure the controller is initialized
            getProfileContentPane();
            menuController.setProfileAttributeController(attributeController);
            
        }
        return profileNavigationPane;
    }

    private Pane getProfileContentPane() throws IOException {
        if (profileContentPane == null) {
            URL location = getClass().getResource("/views/ProfileAttribute.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            profileContentPane = loader.load();
            attributeController = loader.getController();
        }
        return profileContentPane;
    }

}
