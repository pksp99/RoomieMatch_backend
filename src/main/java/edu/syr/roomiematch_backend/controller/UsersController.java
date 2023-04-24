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
import edu.syr.roomiematch_backend.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UsersController implements UsersApi {

    @Autowired
    private GetUserGroupsListService getUserGroupsListService;

    @Autowired
    private GetUserDetailService getUserDetailService;

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired

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
        return createUserService.createUser(xUserId, body);
    }

    @Override
    public ResponseEntity<User> getUserDetail(String userId, String xUserId) {
        return getUserDetailService.getUserDetail(userId, xUserId);
    }

    @Override
    public ResponseEntity<GetUserGroupsResponse> getUserGroupsList(String xUserId) {
        return getUserGroupsListService.getUserGroupList(xUserId);
    }

    @Override
    public ResponseEntity<User> updateUser(String xUserId, String userId, UserAttributes body) {
        return updateUserService.upadateUser(xUserId, userId, body);
    }


}
