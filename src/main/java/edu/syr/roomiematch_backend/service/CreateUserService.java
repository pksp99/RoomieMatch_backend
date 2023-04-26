package edu.syr.roomiematch_backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.dao.Group;
import edu.syr.roomiematch_backend.dao.nonIndexClasses.PreferredAttributes;
import edu.syr.roomiematch_backend.dao.nonIndexClasses.UserAttributes;
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
import java.util.Map;


@Service
@Slf4j
public class CreateUserService {

    @Autowired
    RestHighLevelClient restHighLevelClient;


    public ResponseEntity<String> createUser(Group group, String xUserId) {

        //Creating new objectmapper for converting Object to json string
        ObjectMapper objectMapper = new ObjectMapper();

        UserAttributes userAttributes = group.getUser_attributes();

        PreferredAttributes preferredAttributes = group.getPreferred_attributes();

        //Make index request for new 'groups' Document
        IndexRequest request = new IndexRequest("groups");


        request.source(
                new HashMap<String, Object>() {{
                    put("userId", group.getUserId());
                    put("email", group.getEmail());
                    put("groupId", group.getGroupId());
                    put("user_attributes", new HashMap<String, Object>() {{
                        put("name", userAttributes.getName());
                        put("intro", userAttributes.getIntro());
                        put("profile_image", userAttributes.getProfile_image());
                        put("gender",userAttributes.getGender());
                        put("age", userAttributes.getAge());
                        put("monthly_budget", userAttributes.getMonthly_budget());
                        put("major", userAttributes.getMajor());
                        put("date_available", userAttributes.getDate_available());
                        put("food_preference", userAttributes.getFood_preference());
                        put("cover_images", new ArrayList<Map<String, Object>>() {{
                            add(new HashMap<String, Object>() {{
                                put("image_url", "https://example.com/image1.png");
                            }});
                            add(new HashMap<String, Object>() {{
                                put("image_url", "https://example.com/image2.png");
                            }});
                        }});
                        put("bio",userAttributes.getBio());
                        put("hobbies", new ArrayList<String>() {{
                            add("coding");
                            add("music");
                            add("movies");
                        }});
                        put("cleanliness", userAttributes.getCleanliness());
                        put("sleep_schedule", userAttributes.getSleep_schedule());
                        put("smoking", userAttributes.isPartying());
                        put("partying", userAttributes.isPartying());
                        put("pet_friendly", userAttributes.isPet_friendly());
                    }});
                    put("preferred_attributes", new HashMap<String, Object>() {{
                        put("gender", preferredAttributes.getGender());
                        put("hobbies", new ArrayList<String>() {{
                            add("coding");
                            add("music");
                            add("movies");
                        }});
                        put("food_preference",preferredAttributes.getFood_preference());
                        put("cleanliness", preferredAttributes.getCleanliness());
                        put("sleep_schedule",preferredAttributes.getSleep_schedule());
                        put("smoking",preferredAttributes.isSmoking());
                        put("partying", preferredAttributes.isPartying());
                        put("pet_friendly", preferredAttributes.isPet_friendly());
                    }});
                }}
        );


        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
        }


        //Make index request for new 'usergrouplinks' Document
        request = new IndexRequest("usergrouplinks");

        Map<String, Object> userGroupLinksMap = new HashMap<>();

        userGroupLinksMap.put("userId", group.getUserId());
        userGroupLinksMap.put("groupId", group.getGroupId());

        request.source(userGroupLinksMap);

        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
        }

        return ResponseEntity.ok(group.getUserId());
    }
}
