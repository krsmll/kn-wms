package com.example.knwms.api;

import com.example.knwms.dto.GroupDto;
import com.example.knwms.model.Group;
import com.example.knwms.services.GroupService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-groups")
@NoArgsConstructor
public class GroupController {
    @Autowired
    private GroupService groupService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public @ResponseBody
    List<GroupDto> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();

        return groups.stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/active",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public @ResponseBody
    List<GroupDto> getActiveGroups() {
        List<Group> groups = groupService.getActiveGroups();

        return groups.stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/inactive",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public @ResponseBody
    List<GroupDto> getInactiveGroups() {
        List<Group> groups = groupService.getInactiveGroups();

        return groups.stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public @ResponseBody
    GroupDto getGroupById(@PathVariable long id) {
        Group group = groupService.getGroupById(id);
        return new GroupDto(group);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/search/{text}",
            method = RequestMethod.GET,
            produces = {"application/json"})
    public @ResponseBody
    List<GroupDto> findGroupsByName(@PathVariable String text) {
        List<Group> groups = groupService.findGroupsByName(text);

        return groups.stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
    public @ResponseBody
    GroupDto createGroup(@RequestBody GroupDto groupDto, HttpServletRequest request, HttpServletResponse response) {
        Group createdGroup = groupService.createGroup(groupDto);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdGroup.getId()).toString());

        return new GroupDto(createdGroup);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            consumes = {"application/json"},
            produces = {"application/json"})
    public @ResponseBody
    GroupDto updateGroup(@RequestBody GroupDto groupDto, HttpServletRequest request, HttpServletResponse response) {
        Group updatedGroup = groupService.updateGroup(groupDto);
        response.setHeader("Location", request.getRequestURL().append("/").append(updatedGroup.getId()).toString());

        return new GroupDto(updatedGroup);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/activate", method = RequestMethod.PUT)
    public void activateGroup(@PathVariable long id) {
        groupService.setGroupStatus(id, true);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/deactivate", method = RequestMethod.PUT)
    public void deactivate(@PathVariable long id) {
        groupService.setGroupStatus(id, false);
    }
}
