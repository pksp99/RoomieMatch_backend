package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.UserIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserIndexRepository extends ElasticsearchRepository<UserIndex,String> {
    UserIndex findByUserId(String userId);

}
