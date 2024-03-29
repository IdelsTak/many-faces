/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.manyfaces.model.Group;
import com.manyfaces.model.Profile;
import com.manyfaces.spi.GroupsRepository;
import com.manyfaces.spi.Registry;
import com.manyfaces.spi.RootComponent;
import com.manyfaces.ui.BrowserProfileList;
import com.manyfaces.ui.Help;
import com.manyfaces.ui.MyAccountPreferences;
import com.manyfaces.ui.Plugins;
import com.manyfaces.ui.ProfileEditHome;
import com.manyfaces.ui.ProfileEditHome.EditType;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class HomeMenuController {

    private static final Logger LOG;
    private static final Lookup LOOKUP = Lookup.getDefault();
    @FXML
    private VBox homeMenuBox;
    @FXML
    private ToggleGroup menuGroup;
    @FXML
    private RadioButton homeToggle;
    @FXML
    private RadioButton newProfileToggle;
    @FXML
    private RadioButton accountToggle;
    @FXML
    private RadioButton pluginsToggle;
    @FXML
    private RadioButton helpToggle;
    @FXML
    private JFXButton groupSettingsButton;
    @FXML
    private JFXListView<Group> groupsList;
    private Pane contentPane;
    private ObservableList<Group> groups;

    static {
        LOG = Logger.getLogger(HomeMenuController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        menuGroup.getToggles().forEach(this::removeRadioButtonStyling);
        helpToggle.setOnAction(e -> {
            contentPane.getChildren().setAll(new Help().getPane());
        });
        pluginsToggle.setOnAction(e -> {
            contentPane.getChildren().setAll(new Plugins().getPane());
        });
        accountToggle.setOnAction(e -> {
            contentPane.getChildren().setAll(new MyAccountPreferences().getPane());
        });
        newProfileToggle.setOnAction(e -> {
            //Announce that the profile that was passed 
            //to this method is the one that all clients
            //should be keen on from now on
            LOOKUP.lookup(Registry.class).setAll(new Profile(""));
            
            homeToggle.setSelected(true);
            homeToggle.fireEvent(new ActionEvent(null, null));
            
            LOOKUP.lookup(RootComponent.class)
                    .setContent(new ProfileEditHome(EditType.CREATE).getPane());
        });
        homeToggle.setOnAction(e -> {
            contentPane.getChildren().setAll(new BrowserProfileList().getPane());
        });
        groupSettingsButton.setOnAction(e -> {

            URL location = getClass().getResource("/views/EditGroupsDialog.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Pane pane = null;
            EditGroupsDialogController controller = null;

            try {
                pane = loader.load();
                controller = loader.getController();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }

            if (pane != null && controller != null) {
                JFXDialog dialog = new JFXDialog();
                dialog.setContent(pane);
                dialog.setTransitionType(JFXDialog.DialogTransition.BOTTOM);
                controller.setDialog(dialog);
                dialog.show(LOOKUP.lookup(RootComponent.class).getRoot());
            }

        });

        groups = LOOKUP.lookup(GroupsRepository.class).findAll();

        groups.addListener((Change<? extends Group> change) -> {
            Platform.runLater(() -> groupsList.getItems().setAll(groups));
        });

        groupsList.getItems().setAll(groups);
    }

    public void setContentPane(Pane contentPane) {
        this.contentPane = contentPane;

        URL styleUrl = getClass().getResource("/styles/content-area.css");
        this.contentPane.getStylesheets().add(styleUrl.toExternalForm());

        this.contentPane.getStyleClass().add("content-area");

        homeToggle.setSelected(true);
        homeToggle.fireEvent(new ActionEvent(null, null));
    }

    private void removeRadioButtonStyling(Toggle toggle) {
        ((Styleable) toggle).getStyleClass().remove("radio-button");
    }
}
