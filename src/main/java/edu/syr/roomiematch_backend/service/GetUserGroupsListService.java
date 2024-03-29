package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.model.GetUserGroupsResponse;
import edu.syr.roomiematch_backend.model.UserGroup;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GetUserGroupsListService {


    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    public ResponseEntity<GetUserGroupsResponse> getUserGroups(String xUserId, Boolean isRecommendedUsers) {

        //Fetch All user groups
        Iterable<UserGroupIndex> userGroupIndexIterable = userGroupIndexRepository.findAll();
        List<UserGroup> userGroupList = new ArrayList<>();

        log.info("xuserID: " + xUserId);
        userGroupIndexIterable.forEach( userGroupIndex -> {

            if(!userGroupIndex.getUser_ids().contains(xUserId)) {
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
