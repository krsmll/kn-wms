package com.example.knwms.services;

import com.example.knwms.dto.GroupDto;
import com.example.knwms.exceptions.NotFoundException;
import com.example.knwms.model.Group;
import com.example.knwms.repositories.GroupRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(long id) {
        Optional<Group> group = groupRepository.findById(id);

        return group.orElseThrow(() -> new NotFoundException(Group.class.getSimpleName(), id));
    }

    public List<Group> findGroupsByName(String name) {
        List<Group> groups = groupRepository.findByNameContainingIgnoreCase(name);
        if (groups.size() == 0) {
            throw new NotFoundException(Group.class.getSimpleName(), name);
        }

        return groups;
    }

    public Group updateGroup(GroupDto groupDto) {
        Group group = new Group(groupDto.getId(), groupDto.getName(), groupDto.isActive());

        return groupRepository.save(group);
    }

    public Group createGroup(GroupDto groupDto) {
        Group group = new Group(null, groupDto.getName(), groupDto.isActive());

        return groupRepository.save(group);
    }

    public void setGroupStatus(long id, boolean status) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new NotFoundException(Group.class.getSimpleName(), id));
        group.setActive(status);

        groupRepository.save(group);
    }

    public List<Group> getActiveGroups() {
        return groupRepository.getActiveGroups();
    }

    public List<Group> getInactiveGroups() {
        return groupRepository.getInactiveGroups();
    }
}
