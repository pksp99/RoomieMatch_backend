package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.Group;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends ElasticsearchRepository<Group,String> {
    Group findByUserId(String userId);

}
