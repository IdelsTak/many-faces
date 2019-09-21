/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.manyfaces.model.Group;
import com.manyfaces.spi.GroupsRepository;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class EditGroupsDialogController {

    private static final Logger LOG;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private StackPane listActionsPane;
    @FXML
    private JFXButton addNewgroupButton;
    @FXML
    private JFXTextField groupNameField;
    @FXML
    private JFXButton applyGroupNameButton;
    @FXML
    private JFXListView<Group> groupsListView;
    @FXML
    private Text noGroupsFoundText;

    static {
        LOG = Logger.getLogger(EditGroupsDialogController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        BooleanProperty nameFieldprop = groupNameField.visibleProperty();
        BooleanBinding nameFieldNotProp = nameFieldprop.not();

        addNewgroupButton.visibleProperty().bind(nameFieldNotProp);
        applyGroupNameButton.visibleProperty().bind(nameFieldprop);

        addNewgroupButton.setOnAction(e -> groupNameField.setVisible(true));

        Lookup globalLookup = Lookup.getDefault();
        GroupsRepository repository = globalLookup.lookup(GroupsRepository.class);

        ObservableList<Group> groups = repository.findAll();

        groups.addListener((Change<? extends Group> c) -> {
            LOG.log(Level.INFO, "Groups list change event occured: {0}", c);

            noGroupsFoundText.setVisible(c.getList().isEmpty());
            groupsListView.setVisible(!c.getList().isEmpty());

//            groupsListView.setCellFactory(listView -> new GroupCell());
//            groupsListView.getItems().setAll(groups);
//            groupsListView.refresh();
            Platform.runLater(() -> initListView(groups));
        });

        noGroupsFoundText.setVisible(groups.isEmpty());
        groupsListView.setVisible(!groups.isEmpty());

        initListView(groups);
    }

    void setDialog(JFXDialog dialog) {
        if (dialog == null) {
            throw new IllegalArgumentException("Dialog should not be null");
        }
        closeButton.setOnAction(e -> dialog.close());
        cancelButton.setOnAction(e -> dialog.close());
    }

    private void initListView(ObservableList<Group> groups) {
        groupsListView.setCellFactory(listView -> new GroupCell());
        groupsListView.getItems().setAll(groups);
    }

    private static class GroupCell extends ListCell<Group> {

        private GroupCell() {
        }

        @Override
        protected void updateItem(Group group, boolean empty) {
            super.updateItem(group, empty);

            LOG.log(Level.INFO,
                    "Cell updating... group: {0}; is empty? {1}",
                    new Object[]{group, empty});

            if (group != null && !empty) {
                setGraphic(getHBox(group));
            } else {
                setGraphic(new HBox(new Label("<empty>")));
            }
        }

        private HBox getHBox(Group group) {
            URL location = getClass().getResource("/views/GroupListCell.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            HBox hBox = null;
            try {
                hBox = loader.load();
                GroupListCellController controller = loader.getController();
                controller.setGroup(group);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            return hBox;
        }

    }
}
