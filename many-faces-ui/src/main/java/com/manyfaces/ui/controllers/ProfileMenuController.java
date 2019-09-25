/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.manyfaces.spi.RootComponent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Pane;
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
    @FXML
    private ToggleGroup profileMenuGroup;
    @FXML
    private RadioButton overviewToggle;
    @FXML
    private RadioButton proxyToggle;
    @FXML
    private RadioButton timezoneToggle;
    @FXML
    private RadioButton webRtcToggle;
    @FXML
    private RadioButton geoLocationToggle;
    @FXML
    private RadioButton navigatorToggle;
    @FXML
    private RadioButton fontsToggle;
    @FXML
    private RadioButton devicesToggle;
    @FXML
    private RadioButton hardwareToggle;
    @FXML
    private RadioButton extensionsToggle;
    @FXML
    private RadioButton storageToggle;
    @FXML
    private RadioButton browserPluginsToggle;
    @FXML
    private RadioButton otherToggle;

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

    public void setProfileAttributeController(ProfileAttributeController controller) {
        String message = "Profile attribute controller should not be null";
        ProfileAttributeController kontroller = Objects.requireNonNull(
                controller,
                message);

        profileMenuGroup.selectedToggleProperty()
                .addListener((o, ov, nv) -> {
                    if (nv != null) {
                        kontroller.setHeaderText(((Labeled) nv).getText());
                    }
                });

        Toggle selectedToggle = profileMenuGroup.getSelectedToggle();
        kontroller.setHeaderText(((Labeled) selectedToggle).getText());

        overviewToggle.setOnAction(e -> {
            URL location = getClass().getResource("/views/ProfileOverview.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Pane pane = null;

            try {
                pane = loader.load();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }

            kontroller.setContent(pane);
        });
        
        overviewToggle.fireEvent(new ActionEvent(null, null));
    }
}
