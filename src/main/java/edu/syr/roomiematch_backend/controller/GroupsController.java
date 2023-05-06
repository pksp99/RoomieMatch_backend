package edu.syr.roomiematch_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.api.GroupsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class GroupsController implements GroupsApi {
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> makeGroup(String groupId1, String groupId2) {
        return null;
    }
}
