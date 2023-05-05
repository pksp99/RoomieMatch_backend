package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.dao.UserIndex;
import edu.syr.roomiematch_backend.model.*;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import edu.syr.roomiematch_backend.repository.UserIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class CreateUserService {

    @Autowired
    RestHighLevelClient restHighLevelClient;


    @Autowired
    UserIndexRepository userIndexRepository;

    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;


    public ResponseEntity<UserCreatedResponse> createUser(User body, String xUserId) {

        // TBD : Logic to update in the same call
        UserIndex existingUserIndex = userIndexRepository.findByUserId(body.getUserId());
        UserIndex userIndex = new UserIndex();
        UserCreatedResponse userCreatedResponse = new UserCreatedResponse();
        UserGroupIndex userGroupIndex = new UserGroupIndex();

        //If the user doesn't exist, create user
        if(existingUserIndex==null) {

            userIndex.setUserId(body.getUserId());
            userIndex.setGroupId(body.getGroupId());
            userIndex.setEmail(body.getEmail());
            userIndex.setPreferredAttributes(body.getPreferredAttributes());
            userIndex.setUserAttributes(body.getUserAttributes());

            try {
                userIndexRepository.save(userIndex);
            } catch (Exception e) {
            }


            //Create a User-Group-Link for the user
            UserGroupLink userGroupLink = new UserGroupLink();
            userGroupLink.setUserId(body.getUserId());
            userGroupLink.setGroupId(body.getGroupId());

            try {
                userGroupLinkRepository.save(userGroupLink);
            } catch (Exception e) {
            }



            //Create a List containing this user
            List<User> userList = new ArrayList<>();
            userList.add(body);
            userGroupIndex.setUsers(userList);

            userGroupIndex.setUser_ids(Collections.singletonList(userIndex.getUserId()));
            userGroupIndex.setGroupId(userIndex.getGroupId());
            userGroupIndex.setUser_count(1);
            userGroupIndex.setGroup_info("User group");

            try {
                userGroupIndexRepository.save(userGroupIndex);
            } catch (Exception e) {
            }


            userCreatedResponse.setUserId(body.getUserId());

            return new ResponseEntity<>(userCreatedResponse, HttpStatus.CREATED);

        }

        //Delete the document in User Index, User group Index
        try {
            userIndexRepository.delete(existingUserIndex);
        }
        catch(Exception e){}


        //Find and delete the document in User Group Index. This is temporary code for demo
        UserGroupIndex existingUserGroupIndex = userGroupIndexRepository.findByGroupId(body.getGroupId());

        try{
            userGroupIndexRepository.delete(existingUserGroupIndex);
        }
        catch (Exception e){}



        userIndex.setUserId(body.getUserId());
        userIndex.setGroupId(body.getGroupId());
        userIndex.setEmail(body.getEmail());
        userIndex.setPreferredAttributes(body.getPreferredAttributes());
        userIndex.setUserAttributes(body.getUserAttributes());

        try {
            userIndexRepository.save(userIndex);
        } catch (Exception e) {
        }




        //Creating a User Group Index document
        //Create a List containing this user
        List<User> userList = new ArrayList<>();
        userList.add(body);
        userGroupIndex.setUsers(userList);

        userGroupIndex.setUser_ids(Collections.singletonList(userIndex.getUserId()));
        userGroupIndex.setGroupId(userIndex.getGroupId());
        userGroupIndex.setUser_count(1);
        userGroupIndex.setGroup_info("User group");

        try {
            userGroupIndexRepository.save(userGroupIndex);
        } catch (Exception e) {
        }


        userCreatedResponse.setUserId(body.getUserId());
        return new ResponseEntity<>(userCreatedResponse, HttpStatus.CREATED);

    }
}
