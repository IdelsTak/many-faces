/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import java.util.Objects;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAttributeController {

    @FXML
    private Text headerText;
    @FXML
    private AnchorPane attributeContentPane;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
    }

    public void setHeaderText(String text) {
        headerText.setText(text);
    }

    public void setContent(Node content) {
        String message = "Content node should not be null";
        Node kontent = Objects.requireNonNull(content, message);

        Platform.runLater(() -> attributeContentPane.getChildren()
                .setAll(kontent));
    }

}
