package edu.syr.roomiematch_backend.service;

import edu.syr.roomiematch_backend.dao.Group;
import edu.syr.roomiematch_backend.model.GetUserGroupsResponse;
import edu.syr.roomiematch_backend.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GetUserGroupsListService {

    @Autowired
    GroupRepository groupRepository;

    public ResponseEntity<HashMap<String,Object>> getUserGroups(String xUserId, Boolean isRecommendedUsers) {

        //Currently not using isRecommendedUsers
        Iterable<Group> userGroups = groupRepository.findAll();

        //Convert iterable to List using Java8 stream.
        List<Group> userGroupsList = StreamSupport.stream(userGroups.spliterator(), false)
                .collect(Collectors.toList());

        HashMap<String,Object> userGroupsMap = new HashMap<>();
        userGroupsMap.put("userGroups",userGroupsList);

        return ResponseEntity.ok(userGroupsMap);

    }
}
