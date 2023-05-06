package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MakeGroupService {

    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;
    public ResponseEntity<Void> makeGroup(String groupId1, String groupId2) {
        UserGroupIndex userGroupIndex1 = userGroupIndexRepository.findByGroupId(groupId1);
        UserGroupIndex userGroupIndex2 = userGroupIndexRepository.findByGroupId(groupId2);
        userGroupIndex1.getUser_ids().addAll(userGroupIndex2.getUser_ids());
        userGroupIndex1.getUsers().addAll(userGroupIndex2.getUsers());
        userGroupIndex1.setUser_count(userGroupIndex1.getUser_count() + userGroupIndex2.getUser_count());
        userGroupIndexRepository.save(userGroupIndex1);

        for(String userId: userGroupIndex2.getUser_ids()) {
            UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(userId);
            userGroupLink.setGroupId(userGroupIndex1.getGroupId());
            userGroupLinkRepository.save(userGroupLink);
        }
        userGroupIndexRepository.delete(userGroupIndex2);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
