package edu.syr.roomiematch_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.api.DismissUndismissApi;
import edu.syr.roomiematch_backend.model.GroupList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class DismissUndismissController implements DismissUndismissApi {
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> dismissGroup(String groupId, String xUserId) {
        return null;
    }

    @Override
    public ResponseEntity<GroupList> getDismisses(String xUserId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> undismissGroup(String groupId, String xUserId) {
        return null;
    }
}
