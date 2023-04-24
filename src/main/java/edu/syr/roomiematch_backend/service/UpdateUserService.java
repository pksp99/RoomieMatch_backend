package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.model.UserAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {
    public ResponseEntity<User> upadateUser(String xUserId, String userId, UserAttributes body) {
        return null;
    }
}
