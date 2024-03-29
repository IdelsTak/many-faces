/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.ProfilesRepository;
import com.manyfaces.spi.Registry;
import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.ProfileEditHome;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAttributeController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private Label profileNameLabel;
    @FXML
    private Label osLabel;
    @FXML
    private Text headerText;
    @FXML
    private AnchorPane attributeContentPane;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXButton updateButton;

    static {
        LOG = Logger.getLogger(ProfileAttributeController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        cancelButton.setOnAction(e -> showCancelDialog());
    }

    public void setHeaderText(String text) {
        headerText.setText(text);
    }

    public void setEditType(ProfileEditHome.EditType editType) {
        switch (editType) {
            case CREATE:
                updateButton.setText("Create profile");
                updateButton.setOnAction(e -> createProfile());
                break;
            case EDIT:
                updateButton.setText("Update profile");
                updateButton.setOnAction(e -> updateProfile());
                break;
            default:
                throw new IllegalArgumentException("Edit type not known");
        }

        LOOKUP.lookup(Registry.class)
                .getLookup()
                .lookupAll(Profile.class)
                .stream()
                .findFirst()
                .ifPresent(p -> {
                    LOG.log(Level.INFO, "Profile with id to create: {0}", p.getId());

                    profileNameLabel.textProperty().bind(p.nameProperty());
                    osLabel.textProperty().bind(p.osProperty());
                });
    }

    public void setContent(Node content) {
        String message = "Content node should not be null";
        Node kontent = Objects.requireNonNull(content, message);

        Platform.runLater(() -> {
            attributeContentPane.getChildren().setAll(kontent);
            //Ensure the content resizes with the
            //parent anchorpane
            AnchorPane.setTopAnchor(kontent, 0.0);
            AnchorPane.setRightAnchor(kontent, 0.0);
            AnchorPane.setBottomAnchor(kontent, 0.0);
            AnchorPane.setLeftAnchor(kontent, 0.0);
        });
    }

    private void updateProfile() {
        Collection<? extends Profile> profiles = LOOKUP.lookup(Registry.class)
                .getLookup()
                .lookupAll(Profile.class);

        LOG.log(Level.INFO, "Found {0} profiles. Value(s): {1}",
                new Object[]{profiles.size(), profiles});
    }

    private void createProfile() {
        LOOKUP.lookup(Registry.class)
                .getLookup()
                .lookupAll(String.class)
                .stream()
                .findFirst()
                .ifPresent(name -> {
                    LOOKUP.lookup(ProfilesRepository.class).add(name);
                });
    }

    private void showCancelDialog() {
        URL location = getClass().getResource("/views/ProfileCancelDialog.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        Pane pane = null;
        ProfileCancelDialogController controller = null;

        try {
            pane = loader.load();
            controller = loader.getController();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        if (pane != null && controller != null) {
            JFXDialog dialog = new JFXDialog();
            dialog.setContent(pane);
            controller.setDialog(dialog);
            dialog.show(LOOKUP.lookup(RootComponent.class).getRoot());
        }
    }

}
