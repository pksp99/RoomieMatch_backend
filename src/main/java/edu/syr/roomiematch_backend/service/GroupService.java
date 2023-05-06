package edu.syr.roomiematch_backend.service;


import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.dao.UserIndex;
import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import edu.syr.roomiematch_backend.repository.UserIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupService {

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;


    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;


    @Autowired
    UserIndexRepository userIndexRepository;


    //Add group2 to group1
    public ResponseEntity<Void> makeGroup(String groupId1, String groupId2) {


        //Change Groups ID in user Index from GroupId2 to GroupID1
        UserIndex userIndex = userIndexRepository.findByGroupId(groupId2);

        try {
            userIndexRepository.delete(userIndex);
        } catch (Exception e) {
        }


        userIndex.setGroupId(groupId1);

        try {
            userIndexRepository.save(userIndex);
        } catch (Exception e) {
        }

        //Change User Group Link Repository for Group2 User, make it Group1
        UserGroupLink userGroupLink = userGroupLinkRepository.findByGroupId(groupId2);

        try {
            userGroupLinkRepository.deleteById(userGroupLink.getId());
        } catch (Exception e) {
        }


        userGroupLink.setGroupId(groupId1);


        try {
            userGroupLinkRepository.save(userGroupLink);
        } catch (Exception e) {
        }


        //Append user from group2 to group1 in UserGroupIndex Repository, delete group 2

        UserGroupIndex userGroupIndex2 = userGroupIndexRepository.findByGroupId(groupId2);

        List<User> users = userGroupIndex2.getUsers();

        for(User user : users){
            user.setGroupId(groupId1);
        }

        List<String> userIds = userGroupIndex2.getUser_ids();
        Integer count = userGroupIndex2.getUser_count();

        try {
            userGroupIndexRepository.delete(userGroupIndex2);

        } catch (Exception e) {
        }
        UserGroupIndex userGroupIndex1 = userGroupIndexRepository.findByGroupId(groupId1);


        List<User> userGroupIndex1Users = userGroupIndex1.getUsers();
        userGroupIndex1Users.addAll(users);

        Integer userGroupIndex1Count = userGroupIndex1.getUser_count();
        userGroupIndex1Count = userGroupIndex1Count + count;

        List<String> userGroupIndex1CountUserIds = userGroupIndex1.getUser_ids();
        userGroupIndex1CountUserIds.addAll(userIds);

        //Update by deleting and adding

        try {
            userGroupIndexRepository.delete(userGroupIndex1);
        } catch (Exception e) {
        }

        userGroupIndex1.setUsers(userGroupIndex1Users);
        userGroupIndex1.setUser_count(userGroupIndex1Count);
        userGroupIndex1.setUsers(userGroupIndex1Users);


        //Save into DB
        try {
            userGroupIndexRepository.save(userGroupIndex1);
        } catch (Exception e) {
        }


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
