/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXTextField;
import com.manyfaces.model.Group;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class GroupListController {

    private static final Logger LOG;
    @FXML
    private JFXTextField searchField;
    @FXML
    private Accordion accordion;

    static {
        LOG = Logger.getLogger(GroupListController.class.getName());
    }

    /**
     Initializes the controller class.

     @throws java.io.IOException
     */
    @FXML
    public void initialize() throws IOException {

        ObservableList<Group> groups = FXCollections.observableArrayList();

        groups.add(new Group(1, "Unassigned"));
        groups.add(new Group(2, "test group"));

        groups.forEach(group -> {
            accordion.getPanes().add(getGroupRow(group.getGroupNameProperty().get()));
        });

        accordion.expandedPaneProperty().addListener((ob, ov, nv) -> {
            // This value will change to true if there's (at least) 
            //one pane that is in "expanded" state, so we don't have 
            //to collapse anything manually
//            Boolean collapse = false;
//            for (TitledPane pane : accordion.getPanes()) {
//                if (pane.isExpanded()) {
//                    collapse = true;
//                }
//            }
            //Here we already know whether we need to collapse
            //the new pane
//            if ((collapse == true) && (nv != null)) {
//                LOG.log(Level.INFO, "tp to expand; {0}", new Object[]{nv.getId()});
//                Platform.runLater(() -> {
//                    accordion.setExpandedPane(null);
//                });
//            }
            accordion.getPanes()
                    .stream()
                    .filter(pane -> pane.isExpanded())
                    .findFirst()
                    .ifPresent(pane -> {
                        LOG.log(Level.INFO,
                                "selected pane: {0}",
                                new Object[]{pane.getId()});
                        pane.setExpanded(false);
                    });

        });
    }

    private TitledPane getGroupRow(String groupName) {
        URL location = getClass().getResource("/views/GroupListRow.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        TitledPane titledPane = null;

        try {
            titledPane = loader.load();
            titledPane.setId(groupName);
            GroupListRowController controller = loader.getController();

            controller.setGroupName(groupName);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return titledPane;
    }
}
