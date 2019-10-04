/*
 Copyright 2019.
 */
package com.manyfaces.model;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import java.time.LocalDateTime;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**

 @author Hiram K <hiram.kamau@outlook.com>
 */
public class Profile {

    private static final Internet FAKE_INTERNET = new Faker().internet();
    private final SimpleStringProperty idProperty;
    private final SimpleStringProperty nameProperty;
    private final SimpleStringProperty osProperty;
    private final SimpleStringProperty notesProperty;
    private final SimpleObjectProperty<Group> groupProperty;
    private final SimpleObjectProperty<LocalDateTime> lastEditedProperty;

    public Profile(String name) {
        this(FAKE_INTERNET.uuid(),
                name,
                "",
                new Group(new Faker().number().randomDigitNotZero(), "Unassigned"),
                LocalDateTime.now());
    }

    public Profile(String name, Group group) {
        this(FAKE_INTERNET.uuid(), name, "", group, LocalDateTime.now());
    }

    public Profile(String id,
            String name,
            String notes,
            Group group,
            LocalDateTime lastEdited) {
        this.idProperty = new SimpleStringProperty(id);
        this.nameProperty = new SimpleStringProperty(name);
        this.notesProperty = new SimpleStringProperty(notes);
        this.osProperty = new SimpleStringProperty("");
        this.groupProperty = new SimpleObjectProperty<>(group);
        this.lastEditedProperty = new SimpleObjectProperty<>(lastEdited);
    }

    public SimpleStringProperty idProperty() {
        return idProperty;
    }

    public SimpleStringProperty nameProperty() {
        return nameProperty;
    }

    public SimpleStringProperty osProperty() {
        return osProperty;
    }

    public SimpleStringProperty notesProperty() {
        return notesProperty;
    }

    public SimpleObjectProperty<Group> groupProperty() {
        return groupProperty;
    }

    public SimpleObjectProperty<LocalDateTime> lastEditedProperty() {
        return lastEditedProperty;
    }

    public String getId() {
        return idProperty.get();
    }

    public String getName() {
        return nameProperty.get();
    }

    public String getOs() {
        return osProperty.get();
    }

    public void setOs(String os) {
        osProperty.set(os);
    }

    public String getNotes() {
        return notesProperty.get();
    }

    public Group getGroup() {
        return groupProperty.get();
    }

    public LocalDateTime getLastEdited() {
        return lastEditedProperty.get();
    }

    public void setProfileId(String profileId) {
        idProperty.set(profileId);
    }

    public void setProfileName(String profileName) {
        nameProperty.set(profileName);
    }

    public void setNotes(String notes) {
        notesProperty.set(notes);
    }

    public void setGroup(Group group) {
        groupProperty.set(group);
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        lastEditedProperty.set(lastEdited);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.getId());
        hash = 47 * hash + Objects.hashCode(this.getName());
        hash = 47 * hash + Objects.hashCode(this.getNotes());
        hash = 47 * hash + Objects.hashCode(this.getGroup());
        hash = 47 * hash + Objects.hashCode(this.getLastEdited());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getNotes(), other.getNotes())) {
            return false;
        }
        if (!Objects.equals(this.getGroup(), other.getGroup())) {
            return false;
        }
        return Objects.equals(this.getLastEdited(), other.getLastEdited());
    }

    @Override
    public String toString() {
        return getName();
    }

}
