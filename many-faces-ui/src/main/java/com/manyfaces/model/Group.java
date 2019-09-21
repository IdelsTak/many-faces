/*
 Copyright 2019.
 */
package com.manyfaces.model;

import javafx.beans.property.SimpleStringProperty;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Group {

    private final SimpleStringProperty groupNameProperty;

    public Group(String groupName) {
        this.groupNameProperty = new SimpleStringProperty(groupName);
    }

    public SimpleStringProperty getGroupNameProperty() {
        return groupNameProperty;
    }

}
