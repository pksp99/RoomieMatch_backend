package edu.syr.roomiematch_backend.dao;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usergrouplinks")
@Data
public class UserGroupLink {

    @Id
    private String id;

    private String userId;

    private String groupId;

}
