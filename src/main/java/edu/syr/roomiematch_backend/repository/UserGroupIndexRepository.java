package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserGroupIndexRepository extends ElasticsearchRepository<UserGroupIndex,String> {

    UserGroupIndex findByGroupId(String groupId);

}
