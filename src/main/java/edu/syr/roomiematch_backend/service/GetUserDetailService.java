package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.UserIndex;
import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.repository.UserIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserDetailService {


    @Autowired
    private UserIndexRepository userIndexRepository;

    public ResponseEntity<User> getUserDetail(String userId, String xUserId) {
        UserIndex userIndex = userIndexRepository.findByUserId(userId);
        User user = new User();

        if(userIndex!=null) {

            user.setUserId(userIndex.getUserId());
            user.setEmail(userIndex.getEmail());
            user.setGroupId(userIndex.getGroupId());
            user.setUserAttributes(userIndex.getUserAttributes());
            user.setPreferredAttributes(userIndex.getPreferredAttributes());
        }


        return ResponseEntity.ok().body(user);
    }
}
