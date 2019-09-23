/*
 Copyright 2019.
 */
package com.manyfaces.ui.controllers;

import com.manyfaces.spi.RootComponent;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.openide.util.Lookup;

/**
 FXML Controller class

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class PreferencesController {

    private static final Logger LOG;
    @FXML
    private AnchorPane preferencesRootPane;
    @FXML
    private ToggleGroup defaultBrowser;
    @FXML
    private RadioButton foxBrowserToggle;
    @FXML
    private RadioButton chromeBrowserToggle;

    static {
        LOG = Logger.getLogger(PreferencesController.class.getName());

    }

    /**
     Initializes the controller class.
     */
    @FXML
    public void initialize() {
        //Ensure the pane fits the screen
        Lookup l = Lookup.getDefault();
        RootComponent rc = l.lookup(RootComponent.class);
        StackPane sp = rc.getRoot();
        ReadOnlyDoubleProperty rodp = sp.heightProperty();
        SimpleDoubleProperty sdp = new HeightProperty(rodp.get());
        
        sdp.bind(rodp);
        preferencesRootPane.prefHeightProperty().bind(sdp);
    }

    private static class HeightProperty extends SimpleDoubleProperty {

        private HeightProperty(double initialValue) {
            super(initialValue);
        }

        @Override
        public double get() {
            return super.get() - 100.0;
        }
    }

}
