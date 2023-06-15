package edu.syr.roomiematch_backend.dao;

import edu.syr.roomiematch_backend.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;




@Document( "usergroups")
@Data
public class UserGroupIndex {

    @Id
    private String id;

    private String groupId;

    private String group_info = null;

    private int user_count;

    private List<String> user_ids;

    private List<User> users;
}


