package edu.syr.roomiematch_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.api.GroupsApi;
import edu.syr.roomiematch_backend.model.UserGroup;
import edu.syr.roomiematch_backend.service.GetUserGroupService;
import edu.syr.roomiematch_backend.service.MakeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class GroupsController implements GroupsApi {
    @Autowired
    private MakeGroupService makeGroupService;

    @Autowired
    private GetUserGroupService getUserGroupService;
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<UserGroup> getUserGroup(String xUserId) {
        return getUserGroupService.getUserGroup(xUserId);
    }

    @Override
    public ResponseEntity<Void> makeGroup(String groupId1, String groupId2) {
        log.info("Making groups {} and {}", groupId1, groupId2);
        return makeGroupService.makeGroup(groupId1, groupId2);
    }
}
