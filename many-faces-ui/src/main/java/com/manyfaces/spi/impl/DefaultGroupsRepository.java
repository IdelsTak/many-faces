/*
 Copyright 2019.
 */
package com.manyfaces.spi.impl;

import com.manyfaces.model.Group;
import com.manyfaces.spi.GroupsRepository;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = GroupsRepository.class)
public class DefaultGroupsRepository implements GroupsRepository {

    private static final Logger LOG;
    private final ObservableList<Group> groups;
    private int id;

    static {
        LOG = Logger.getLogger(DefaultGroupsRepository.class.getName());
    }

    public DefaultGroupsRepository() {
        this.groups = FXCollections.observableArrayList();
        //Sample data
        groups.add(new Group(1, "Unassigned"));
        groups.add(new Group(2, "test group"));
    }

    @Override
    public void add(String groupName) {
        id += 1;
        groups.add(new Group(id, groupName));
    }

    @Override
    public void update(Group group) {
        groups.stream()
                .filter(g -> {
                    int anId = g.getIdProperty().get();
                    int groupId = group.getIdProperty().get();
                    return anId == groupId;
                })
                .findFirst()
                .ifPresent(g -> {
                    String newGroupName = group.getGroupNameProperty().get();
                    g.getGroupNameProperty().set(newGroupName);
                });
    }

    @Override
    public Optional<Group> findbyId(int id) {
        return groups.stream()
                .filter(group -> group.getIdProperty().get() == id)
                .findFirst();
    }

    @Override
    public ObservableList<Group> findAll() {
        return FXCollections.unmodifiableObservableList(groups);
    }

    @Override
    public void delete(Group group) {
        LOG.log(Level.INFO, "Deleting group: {0}", group);
        boolean removed = groups.remove(group);
        LOG.log(Level.INFO,
                "Group: {0} was removed? {1}",
                new Object[]{group, removed});
    }

}
