package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.LikedGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LikedGroupsRepository extends ElasticsearchRepository<LikedGroup,String> {

}
