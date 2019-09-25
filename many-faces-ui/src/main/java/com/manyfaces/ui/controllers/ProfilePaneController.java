/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToggleNode;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfilePaneController {

    private static final Logger LOG;
    @FXML
    private JFXCheckBox titlePaneCheckBox;
    @FXML
    private TitledPane titledPane;
    @FXML
    private Text profileIdText;
    @FXML
    private HBox titleHbox;
    @FXML
    private Label profileNameLabel;
    @FXML
    private JFXToggleNode expandPaneToggle;
    @FXML
    private JFXButton menuButton;
    private JFXPopup popup;

    static {
        LOG = Logger.getLogger(ProfilePaneController.class.getName());
    }

    /**
     Initializes the controller class.

     @throws java.io.IOException
     */
    @FXML
    public void initialize() throws IOException {
        titledPane.widthProperty().addListener((obs, ov, nv) -> {
            titleHbox.setPrefWidth(nv.doubleValue());
        });

        titledPane.expandedProperty().addListener((obs, ov, nv) -> {
            expandPaneToggle.setSelected(nv);
        });

        expandPaneToggle.selectedProperty().addListener((ob, ov, nv) -> {
            titledPane.setExpanded(nv);
        });

        URL location = getClass().getResource("/views/ProfileActionsPopup.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane actionsPane = loader.load();
        popup = new JFXPopup(actionsPane);
        ProfileActionsPopupController controller = loader.getController();

        controller.setPopup(popup);

        menuButton.setOnAction(e -> {
            popup.show(menuButton,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.RIGHT);
        });
    }

    void setTitledPaneTitle(String profileName) {
        profileNameLabel.setText(profileName);
    }

    void setTitledPaneSelected(boolean selected) {
        titlePaneCheckBox.setSelected(selected);
    }

    void showSelectBoxes(boolean show) {
        if (show) {
            titleHbox.getChildren().set(0, titlePaneCheckBox);
        } else {
            titleHbox.getChildren().remove(titlePaneCheckBox);
        }
    }
}
