package edu.syr.roomiematch_backend.repository;

import edu.syr.roomiematch_backend.dao.LikedGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikedGroupsRepository extends MongoRepository<LikedGroup,String> {


    List<LikedGroup> findByGroupIdMakingLikeAction(String groupId);


    List<LikedGroup> findByGroupIdLiked(String groupId);
}
