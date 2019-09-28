/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class ProfileAdvancedFontsController {

    @FXML
    private VBox fontsBox;
    @FXML
    private Hyperlink moreFontsHyperlink;

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] families = ge.getAvailableFontFamilyNames(Locale.getDefault());

        for (int i = 0; i < 10; i++) {
            String family = families[i];
            fontsBox.getChildren().add(new Label(family));
        }

        moreFontsHyperlink.setText(String.format("...and %d more", (families.length - 11)));

        moreFontsHyperlink.setOnAction(e -> {
            for (int i = 10; i < families.length; i++) {
                String family = families[i];
                fontsBox.getChildren().add(new Label(family));
            }

            moreFontsHyperlink.setVisible(false);
        });
    }

    private Collection<Font> getInstalledFonts() {
        return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
    }

}
