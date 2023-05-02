package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.Group;
import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserDetailService {

    //Returns user by searching on userId

    @Autowired
    private GroupRepository groupRepository;

    public ResponseEntity<Group> getUserDetail(String userId, String xUserId) {
          return ResponseEntity.ok(groupRepository.findByUserId(userId));
    }
}
