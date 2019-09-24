/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToggleNode;
import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.ProfileEditHome;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.openide.util.Lookup;

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
    private JFXListView<Label> actionsList;
    private final ProfileActions profileActions;

    static {
        LOG = Logger.getLogger(ProfilePaneController.class.getName());
    }

    {
        profileActions = new ProfileActions();
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titledPane.widthProperty().addListener((obs, ov, nv) -> {
            titleHbox.setPrefWidth(nv.doubleValue());
        });

        titledPane.expandedProperty().addListener((obs, ov, nv) -> {
            expandPaneToggle.setSelected(nv);
        });

        expandPaneToggle.selectedProperty().addListener((ob, ov, nv) -> {
            titledPane.setExpanded(nv);
        });

        URL styleUrl = getClass().getResource("/styles/profile-pane.css");
        String style = styleUrl.toExternalForm();

        actionsList = new JFXListView<>();
        actionsList.getStylesheets().add(style);

        actionsList.getItems().add(new Label("Edit"));
        actionsList.getItems().add(new Label("Move to a group"));
        actionsList.getItems().add(new Label("Delete"));

        actionsList.selectionModelProperty()
                .getValue()
                .selectedItemProperty()
                .addListener(profileActions);

        popup = new JFXPopup(actionsList);

//        popup.showingProperty().addListener((o, ov, nv) -> {
//            LOG.log(Level.INFO,
//                    "\n++++++++++++++++++++++++++++"
//                    + "\nActions popup event occured"
//                    + "\n- old val: {0}"
//                    + "\n- new val: {1}",
//                    new Object[]{ov,
//                                 nv});
//
//            if (nv != null && nv == true) {
//                actionsList.getSelectionModel().clearSelection();
//            }
//        });

        popup.setOnShowing(e -> {
            actionsList.getSelectionModel().clearSelection();
        });

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

    private class ProfileActions implements ChangeListener<Label> {

        private ProfileActions() {
        }

        @Override
        public void changed(
                ObservableValue<? extends Label> observable,
                Label oldValue,
                Label newValue) {

            LOG.log(Level.INFO,
                    "\n++++++++++++++++++++++++++++"
                    + "\nActions list event occured"
                    + "\n- old val: {0}"
                    + "\n- new val: {1}",
                    new Object[]{oldValue,
                                 newValue});

            if (newValue == null) {
                return;
            }

            switch (newValue.getText()) {
                case "Edit":
                    LOG.log(Level.INFO, "Show profile edit home");
                    Lookup lkp = Lookup.getDefault();
                    RootComponent rc = lkp.lookup(RootComponent.class);

                    rc.setContent(new ProfileEditHome().getPane());

                    LOG.log(Level.INFO, "Hiding popup...");

                    if (popup.isShowing()) {
//                        actionsList.getSelectionModel().clearSelection();
//                        actionsList.getSelectionModel().select(-1);
                        popup.hide();
                    }
                    return;
                case "Move to a group":
                    LOG.log(Level.INFO, "Move to a group");
                    return;
                case "Delete":
                    LOG.log(Level.INFO, "Delete");
                    return;
                default:
                    throw new IllegalArgumentException("Action not known");

            }
        }
    }

}
