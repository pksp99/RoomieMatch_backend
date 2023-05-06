package edu.syr.roomiematch_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.api.LikeDislikeApi;
import edu.syr.roomiematch_backend.model.GroupList;
import edu.syr.roomiematch_backend.model.LikeResponse;
import edu.syr.roomiematch_backend.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
@RestController
public class LikeDislikeController implements LikeDislikeApi {

    @Autowired
    ActionService actionService;


    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> dislikeGroup(String groupId, String xUserId) {
        return actionService.performDislikeAction(groupId,xUserId);
    }


    @Override
    public ResponseEntity<GroupList> getLikes(String xUserId) {
        return actionService.getlikes(xUserId);
    }

    @Override
    public ResponseEntity<LikeResponse> likeGroup(String groupId, String xUserId) {
        return actionService.performLikeAction(groupId,xUserId);
    }
}
