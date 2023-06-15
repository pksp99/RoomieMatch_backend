package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.model.UserGroup;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserGroupService {
    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;

    public ResponseEntity<UserGroup> getUserGroup(String xUserId) {
        UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(xUserId);
        if (userGroupLink == null) {
            return ResponseEntity.ok(null);
        }
        UserGroupIndex userGroupIndex = userGroupIndexRepository.findByGroupId(userGroupLink.getGroupId());
        UserGroup userGroup = new UserGroup();
        userGroup.setUsers(userGroupIndex.getUsers());
        userGroup.setUserIds(userGroupIndex.getUser_ids());
        userGroup.setGroupId(userGroupIndex.getGroupId());
        userGroup.setGroupInfo(userGroupIndex.getGroup_info());
        userGroup.setUserCount(userGroupIndex.getUser_count());

        return ResponseEntity.ok().body(userGroup);
    }
}
