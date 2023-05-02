package edu.syr.roomiematch_backend.dao;

import edu.syr.roomiematch_backend.dao.nonIndexClasses.PreferredAttributes;
import edu.syr.roomiematch_backend.dao.nonIndexClasses.UserAttributes;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "groups")
@Data
public class Group {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Keyword)
    private String groupId;

    @Field(type = FieldType.Object)
    private UserAttributes user_attributes;

    @Field(type = FieldType.Object)
    private PreferredAttributes preferred_attributes;


}


