package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.LikedGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LikedGroupsRepository extends ElasticsearchRepository<LikedGroup,String> {


    List<LikedGroup> findByGroupIdMakingLikeAction(String groupId);


    List<LikedGroup> findByGroupIdLiked(String groupId);
}
