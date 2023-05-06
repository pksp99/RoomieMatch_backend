package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.UserGroupLink;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserGroupLinkRepository extends MongoRepository<UserGroupLink, String> {

    UserGroupLink findByUserId(String userId);

}
