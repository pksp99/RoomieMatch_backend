package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.LikedGroup;
import edu.syr.roomiematch_backend.dao.UserGroupIndex;
import edu.syr.roomiematch_backend.dao.UserGroupLink;
import edu.syr.roomiematch_backend.model.GroupList;
import edu.syr.roomiematch_backend.model.GroupListGroups;
import edu.syr.roomiematch_backend.model.LikeResponse;
import edu.syr.roomiematch_backend.model.LikeResponseGroup;
import edu.syr.roomiematch_backend.repository.LikedGroupsRepository;
import edu.syr.roomiematch_backend.repository.UserGroupIndexRepository;
import edu.syr.roomiematch_backend.repository.UserGroupLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ActionService {


    @Autowired
    UserGroupLinkRepository userGroupLinkRepository;

    @Autowired
    LikedGroupsRepository likedGroupsRepository;


    @Autowired
    UserGroupIndexRepository userGroupIndexRepository;

    public ResponseEntity<LikeResponse> performLikeAction(String groupId, String xUserId) {

        LikeResponse likeResponse = new LikeResponse();
        LikeResponseGroup likeResponseGroup = new LikeResponseGroup();


        UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(xUserId);
        LikedGroup likedGroup = new LikedGroup();
        likedGroup.setGroupIdLiked(groupId);
        likedGroup.setGroupIdMakingLikeAction(userGroupLink.getGroupId());

        try {
            likedGroupsRepository.save(likedGroup);
        } catch (Exception e) {
        }


        List<LikedGroup> likedGroupList = likedGroupsRepository.findByGroupIdMakingLikeAction(groupId);


        //Check all likes of liked Group, if he/she has liked current user before
        if (likedGroupList != null) {
            for (LikedGroup likedGroup1 : likedGroupList) {
                if (likedGroup1.getGroupIdLiked().equalsIgnoreCase(likedGroup.getGroupIdMakingLikeAction())) {
                    likeResponse.setMutualLike(true);
                    List<String> user_names = new ArrayList<>();
                    List<String> user_ids = new ArrayList<>();
                    List<String> group_ids = new ArrayList<>();



                    //Get user details from both groups and add it in response body
                    UserGroupIndex userGroupIndex = userGroupIndexRepository.findByGroupId(groupId);
                    UserGroupIndex userGroupIndex2 = userGroupIndexRepository.findByGroupId(userGroupLink.getGroupId());

                    group_ids.add(userGroupIndex.getGroupId());
                    group_ids.add(userGroupIndex2.getGroupId());
                    likeResponseGroup.setGroupIds(group_ids);

                    user_ids.addAll(userGroupIndex.getUser_ids());
                    user_ids.addAll(userGroupIndex2.getUser_ids());
                    likeResponseGroup.setUserIds(user_ids);


                    //Add user names from first group
                    userGroupIndex.getUsers().forEach(User -> {
                        user_names.add(User.getUserAttributes().getName());
                    });

                    //Add user names from second group

                    userGroupIndex2.getUsers().forEach(User -> {
                        user_names.add(User.getUserAttributes().getName());
                    });

                    likeResponseGroup.setUserNames(user_names);
                    likeResponse.setGroup(likeResponseGroup);
                    return ResponseEntity.ok(likeResponse);

                }
            }
        }

        // Both havent liked each other
        likeResponse.setMutualLike(false);
        likeResponse.setGroup(null);
        return ResponseEntity.ok(likeResponse);

    }

    public ResponseEntity<Void> performDislikeAction(String groupId, String xUserId) {

        UserGroupLink userGroupLink = userGroupLinkRepository.findByUserId(xUserId);

        if (userGroupLink == null) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        Iterable<LikedGroup> documents = likedGroupsRepository.findAll();
        List<LikedGroup> likedGroupsList = new ArrayList<>();
        for (LikedGroup likedGroup : documents) {
            if (likedGroup.getGroupIdLiked().equalsIgnoreCase(groupId) && likedGroup.getGroupIdMakingLikeAction().equalsIgnoreCase(userGroupLink.getGroupId())) {
                likedGroupsList.add(likedGroup);
            }
        }

        likedGroupsRepository.deleteAll(likedGroupsList);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    public ResponseEntity<GroupList> getlikes(String xUserId) {

        GroupList groupList = new GroupList();


        List<GroupListGroups> groupListGroupsList = new ArrayList<>();

        UserGroupLink userGroupLink =  userGroupLinkRepository.findByUserId(xUserId);

        List<LikedGroup> likedGroupsList = likedGroupsRepository.findByGroupIdMakingLikeAction(userGroupLink.getGroupId());

        for(LikedGroup likedGroup : likedGroupsList) {
            UserGroupIndex userGroupIndex = userGroupIndexRepository.findByGroupId(likedGroup.getGroupIdLiked());
            if(userGroupIndex == null) {
                continue;
            }
            GroupListGroups groupListGroups = new GroupListGroups();
            groupListGroups.setGroupId(userGroupIndex.getGroupId());
            groupListGroups.setUserIds(userGroupIndex.getUser_ids());

            List<String> userNames= new ArrayList<>();

            userGroupIndex.getUsers().forEach(User -> {
                userNames.add(User.getUserAttributes().getName());
            });

            groupListGroups.setUserNames(userNames);
            groupListGroupsList.add(groupListGroups);

        }

        groupList.setGroups(groupListGroupsList);

        return ResponseEntity.ok(groupList);

    }
}
