package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.model.UserCreatedResponse;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Slf4j
public class CreateUserService {


    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;


    public ResponseEntity<UserCreatedResponse> createUser(User body, String xUserId) {
        UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(xUserId);
        UserGroupIndex userGroupIndex = new UserGroupIndex();
        if (userGroupLink == null) {
            userGroupLink = new UserGroupLink();
            userGroupLink.setUserId(xUserId);
            userGroupLink.setGroupId(body.getGroupId());
            userGroupLinkRepository.save(userGroupLink);
            userGroupIndex.setGroup_info("Some Group");
            userGroupIndex.setUser_count(1);
            userGroupIndex.setUser_ids(new ArrayList<>());
            userGroupIndex.setUsers(new ArrayList<>());
            userGroupIndex.setGroupId(body.getGroupId());
        } else {
            userGroupIndex = userGroupIndexRepository.findByGroupId(body.getGroupId());
            userGroupIndex.getUser_ids().removeIf(n -> n.equals(body.getUserId()));
            userGroupIndex.getUsers().removeIf(n -> n.getUserId().equals(body.getUserId()));
        }
        userGroupIndex.getUser_ids().add(body.getUserId());
        userGroupIndex.getUsers().add(body);
        userGroupIndexRepository.save(userGroupIndex);

        UserCreatedResponse userCreatedResponse = new UserCreatedResponse();
        userCreatedResponse.setUserId(body.getUserId());
        return new ResponseEntity<>(userCreatedResponse, HttpStatus.CREATED);
    }

}
