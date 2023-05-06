package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserDetailService {


    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;

    public ResponseEntity<User> getUserDetail(String userId, String xUserId) {

        UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(userId);
        if(userGroupLink == null) {
            return ResponseEntity.ok().body(null);
        }
        UserGroupIndex userGroupIndex = userGroupIndexRepository.findByGroupId(userGroupLink.getGroupId());

        return ResponseEntity.ok().body(
                userGroupIndex
                .getUsers()
                        .stream()
                        .filter(user -> user.getUserId().equals(userId)).findAny()
                        .orElse(null));
    }
}
