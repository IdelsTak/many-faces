/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

import com.manyfaces.model.Profile;
import com.manyfaces.spi.ProfilesRepository;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class DefaultProfilesRepository implements ProfilesRepository {

    private final ObservableSet<Profile> profiles;

    /**
     Default constructor.
     */
    public DefaultProfilesRepository() {
        this.profiles = FXCollections.emptyObservableSet();
    }

    @Override
    public void add(String profileName) {
        String message = "Profile name should not be null nor empty";
        if (profileName == null || profileName.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        profiles.add(new Profile(profileName));
    }

    @Override
    public void update(Profile profile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Profile> findbyId(String id) {
        return profiles.stream()
                .filter(profile -> (profile.getProfileId() == null
                                    ? id == null
                                    : profile.getProfileId().equals(id)))
                .findFirst();
    }

    @Override
    public ObservableSet<Profile> findAll() {
        return FXCollections.unmodifiableObservableSet(profiles);
    }

    @Override
    public void delete(Profile profile) {
        profiles.remove(profile);
    }

}
