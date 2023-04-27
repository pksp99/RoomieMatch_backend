package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.UserGroupLink;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserGroupLinkRepository extends ElasticsearchRepository<UserGroupLink, String> {

    UserGroupLink findByUserId(String userId);

}
