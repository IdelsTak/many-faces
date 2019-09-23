/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.github.javafaker.Faker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class PluginsController {

    private static final Logger LOG;
    @FXML
    private JFXTextField searchField;
    @FXML
    private Accordion accordion;

    static {
        LOG = Logger.getLogger(PluginsController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        accordion.addEventFilter(InputEvent.ANY, e -> {
//            LOG.log(Level.INFO, "accordion event occured: {0}", e);

//            if (((Styleable) e.getTarget())
//                    .getStyleClass()
//                    .contains("title")) {
//                e.consume();
//            }
//            if (!((Styleable) e.getTarget())
//                    .getStyleClass()
//                    .contains("toggle-button")) {
//                e.consume();
//            }
//            if (e.getSource().equals(accordion)) {
//                e.consume();
//            }
        });

        accordion.expandedPaneProperty().addListener((ob, ov, nv) -> {
//            accordion.getPanes()
//                    .stream()
//                    .filter(pane -> pane.isExpanded())
//                    .findFirst()
//                    .ifPresent(pane -> {
//                        LOG.log(Level.INFO,
//                                "selected pane: {0}",
//                                new Object[]{pane.getId()});
//                        pane.setExpanded(false);
//                    });

        });

        searchField.textProperty().addListener((o, ov, nv) -> {
            if (nv == null || nv.trim().isEmpty()) {
                Platform.runLater(() -> refreshRows());
                return;
            }

            List<TitledPane> filteredPanes = accordion.getPanes()
                    .stream()
                    .filter(tp -> tp.getId().contains(nv))
                    .collect(Collectors.toList());

            Platform.runLater(() -> accordion.getPanes().setAll(filteredPanes));
        });
    }

    public void setPageHeaderController(PageHeaderController phc) {
        if (phc == null) {
            String message = "PageHeaderController should not be null";
            throw new IllegalArgumentException(message);
        }
        phc.setHeaderText("Plugins");

        Platform.runLater(() -> refreshRows());
    }

    private TitledPane getPluginRow(String pluginName, Node pluginContent) {
        URL location = getClass().getResource("/views/PluginsListRow.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        TitledPane pane = null;

        try {
            pane = loader.load();
            PluginsListRowController controller = loader.getController();
            controller.setPluginName(pluginName);
            controller.setPluginContent(pluginContent);
            controller.setParentAccordion(accordion);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return pane;
    }
    
    private TitledPane getTestPluginRow(String pluginName) {
        URL location = getClass().getResource("/views/PluginsListRow.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        TitledPane pane = null;

        try {
            pane = loader.load();
            PluginsListRowController controller = loader.getController();
            String paragraph = new Faker().lorem().paragraph(15);
            controller.setPluginName(pluginName);
            controller.setTestContent(paragraph);
            controller.setParentAccordion(accordion);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return pane;
    }

    private void refreshRows() {
        accordion.getPanes().add(getPluginRow("IP Teleport", getIPTeleportPane()));
        
//        Internet internet = new Faker().internet();
//
//        for (int i = 0; i < 10; i++) {
//            accordion.getPanes().add(getTestPluginRow(internet.slug()));
//        }
    }
    
    private Node getIPTeleportPane(){
        URL location = getClass().getResource("/views/IPTelePortPane.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        AnchorPane pane = null;
        
        try {
            pane = loader.load();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        return pane;
    }
}
