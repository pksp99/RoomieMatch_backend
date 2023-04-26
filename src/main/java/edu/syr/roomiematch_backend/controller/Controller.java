package edu.syr.roomiematch_backend.controller;

import edu.syr.roomiematch_backend.dao.Group;
import edu.syr.roomiematch_backend.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {

    @Autowired
    private GetUserGroupsListService getUserGroupsListService;

    @Autowired
    private GetUserDetailService getUserDetailService;

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private UpdateUserService updateUserService;


    @Autowired
    private ActionService actionService;



    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody Group group ,@RequestParam String xUserId) throws Exception {
        return createUserService.createUser(group,xUserId);
    }



    @GetMapping("/users/{userId}")
    public ResponseEntity<Group> getUserDetail(@PathVariable String userId, @RequestParam String xUserId) {
        return getUserDetailService.getUserDetail(userId, xUserId);
    }


    @PostMapping("/like/{groupId}")
    public ResponseEntity<String> likeAction(@PathVariable String groupId, @RequestParam String xUserId) {
        return actionService.performLikeAction(groupId, xUserId);
    }

    @PostMapping("/dislike/{groupId}")
    public ResponseEntity<String> dislikeAction(@PathVariable String groupId, @RequestParam String xUserId) {
        return actionService.performDislikeAction(groupId, xUserId);
    }

//    @Override
//    public ResponseEntity<GetUserGroupsResponse> getUserGroupsList(String xUserId, Boolean isRecommendedUsers) {
//        return getUserGroupsListService.getUserGroupList(xUserId, isRecommendedUsers);
//    }
//
//
//    @Override
//    public ResponseEntity<User> updateUser(String xUserId, String userId, UserAttributes body) {
//        return updateUserService.upadateUser(xUserId, userId, body);
//    }


}
