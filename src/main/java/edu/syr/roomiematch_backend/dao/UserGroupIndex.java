package edu.syr.roomiematch_backend.dao;

import edu.syr.roomiematch_backend.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Map;




@Document(indexName = "usergroups")
@Data
public class UserGroupIndex {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String groupId;

    @Field(type = FieldType.Text)
    private String group_info = null;

    @Field(type = FieldType.Integer)
    private int user_count;

    @Field(type = FieldType.Keyword)
    private List<String> user_ids;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<User> users;
}


