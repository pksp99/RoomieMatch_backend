package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserIndex;
import edu.syr.roomiematch_backend.model.GetUserGroupsResponse;
import edu.syr.roomiematch_backend.model.UserGroup;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserGroupsListService {

    @Autowired
    UserIndexRepository userIndexRepository;

    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    public ResponseEntity<GetUserGroupsResponse> getUserGroups(String xUserId, Boolean isRecommendedUsers) {

        //Fetch All user groups
        Iterable<UserGroupIndex> userGroupIndexIterable = userGroupIndexRepository.findAll();
        List<UserGroup> userGroupList = new ArrayList<>();


        userGroupIndexIterable.forEach( userGroupIndex -> {

            if(!userGroupIndex.getUser_ids().get(0).equalsIgnoreCase(xUserId)) {
                UserGroup userGroup = new UserGroup();
                userGroup.setUsers(userGroupIndex.getUsers());
                userGroup.setUserIds(userGroupIndex.getUser_ids());
                userGroup.setGroupId(userGroupIndex.getGroupId());
                userGroup.setGroupInfo(userGroupIndex.getGroup_info());
                userGroup.setUserCount(userGroupIndex.getUser_count());

                userGroupList.add(userGroup);
            }
        });

        //Create response body
        GetUserGroupsResponse userGroupsResponse = new GetUserGroupsResponse();
        userGroupsResponse.setUserGroups(userGroupList);

        return ResponseEntity.ok(userGroupsResponse);
    }
}
