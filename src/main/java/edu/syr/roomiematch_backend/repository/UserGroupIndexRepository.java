package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserGroupIndexRepository extends MongoRepository<UserGroupIndex,String> {

    UserGroupIndex findByGroupId(String groupId);

}
