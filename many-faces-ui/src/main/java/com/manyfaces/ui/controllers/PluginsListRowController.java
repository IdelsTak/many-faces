/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.jfoenix.controls.JFXToggleNode;
import java.util.Objects;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class PluginsListRowController {

    private static final Logger LOG;
    @FXML
    private TitledPane titledPane;
    @FXML
    private HBox titleHbox;
    @FXML
    private AnchorPane pluginContentPane;
    @FXML
    private Label pluginNameLabel;
    @FXML
    private JFXToggleNode openContentToggle;
    @FXML
    private Label sampleContentLabel;

    static {
        LOG = Logger.getLogger(PluginsListRowController.class.getName());
    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        titleHbox.prefWidthProperty().bind(titledPane.widthProperty());
    }

    void setPluginName(String pluginName) {
        String message = "Plugin name should not be null";
        String name = Objects.requireNonNull(pluginName, message);

        pluginNameLabel.setText(name);
        titledPane.setId(name);
    }

    void setPluginContent(Node content) {
        String message = "Content pane should not be null";
        Node kontent = Objects.requireNonNull(content, message);

        Platform.runLater(() -> {
            pluginContentPane.getChildren().setAll(kontent);

            AnchorPane.setTopAnchor(kontent, 0.0);
            AnchorPane.setRightAnchor(kontent, 0.0);
            AnchorPane.setLeftAnchor(kontent, 0.0);
        });

    }

    void setTestContent(String testContent) {
        sampleContentLabel.setText(testContent);
    }

    void setParentAccordion(Accordion accordion) {
        String message = "Plugins accordion should not be null";
        Accordion akkordion = Objects.requireNonNull(accordion, message);

        openContentToggle.setOnAction(e -> {
            if (titledPane.isExpanded()) {
                titledPane.setExpanded(false);
            } else {
                Platform.runLater(() -> akkordion.setExpandedPane(titledPane));
            }
        });
    }

}
