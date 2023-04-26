package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.LikedGroup;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.repository.LikedGroupsRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ActionService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;

    @Autowired
    LikedGroupsRepository likedGroupsRepository;

    public ResponseEntity<String> performLikeAction(String groupId, String xUserId) {

        UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(xUserId);

        if (userGroupLink == null) {
            return ResponseEntity.ok("User with Id : " + xUserId + " not found.");
        }

        //Make index request for new LikedGroup Document
        IndexRequest request = new IndexRequest("likedgroups");
        Map<String, Object> likedGroupsMap = new HashMap<>();

        likedGroupsMap.put("groupIdMakingLikeAction", userGroupLink.getGroupId());
        likedGroupsMap.put("groupIdLiked", groupId);


        request.source(likedGroupsMap);

        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("Exception caught: " + e);
        }

        return ResponseEntity.ok("Done");

    }

    public ResponseEntity<String> performDislikeAction(String groupId, String xUserId) {
       UserGroupLink userGroupLink =  userGroupLinkRepository.findByUserId(xUserId);


       if(userGroupLink == null){
           return ResponseEntity.ok("Error when performing dislike!");
       }
        Iterable<LikedGroup> documents = likedGroupsRepository.findAll();
        List<LikedGroup> likedGroupsList = new ArrayList<>();
        for (LikedGroup likedGroup : documents) {
            if(likedGroup.getGroupIdLiked().equalsIgnoreCase(groupId) && likedGroup.getGroupIdMakingLikeAction().equalsIgnoreCase(userGroupLink.getGroupId())){
                likedGroupsList.add(likedGroup);
            }
        }

        likedGroupsRepository.deleteAll(likedGroupsList);
        return ResponseEntity.ok("Done");
    }
}
