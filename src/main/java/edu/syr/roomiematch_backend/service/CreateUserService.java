package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.model.User;
import edu.syr.roomiematch_backend.model.UserCreatedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    public ResponseEntity<UserCreatedResponse> createUser(String xUserId, User body) {
        return null;
    }
}
