package edu.syr.roomiematch_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.api.UsersApi;
import edu.syr.roomiematch_backend.model.GetUserGroupsResponse;
import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.model.UserAttributes;
import edu.syr.roomiematch_backend.model.UserCreatedResponse;
import edu.syr.roomiematch_backend.service.CreateUserService;
import edu.syr.roomiematch_backend.service.GetUserDetailService;
import edu.syr.roomiematch_backend.service.GetUserGroupsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class UserController implements UsersApi {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private GetUserDetailService getUserDetailService;

    @Autowired
    private GetUserGroupsListService getUserGroupsListService;


    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }


    @Override
    public ResponseEntity<UserCreatedResponse> createUser(String xUserId, User body) {
        return createUserService.createUser(body, xUserId);
    }

    @Override
    public ResponseEntity<User> getUserDetail(String userId, String xUserId) {
        return getUserDetailService.getUserDetail(userId, xUserId);
    }

    @Override
    public ResponseEntity<GetUserGroupsResponse> getUserGroupsList(String xUserId, Boolean isRecommendedUsers) {
        return getUserGroupsListService.getUserGroups(xUserId, isRecommendedUsers);
    }

    @Override
    public ResponseEntity<User> updateUser(String xUserId, String userId, UserAttributes body) {
        return null;
    }
}
